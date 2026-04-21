<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des Produits</title>
    <!-- Bootstrap léger -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">

    <%-- ===== NAVBAR SIMPLE ===== --%>
    <div class="d-flex justify-content-between align-items-center border-bottom pb-2 mb-4">
        <h3 class="m-0">Gestion des Produits</h3>
        <div>
            <span>Bonjour, <strong>${sessionScope.user.username}</strong></span>
            <c:choose>
                <c:when test="${sessionScope.role == 'ADMIN'}">
                    <span class="text-danger">[ADMIN]</span>
                </c:when>
                <c:otherwise>
                    <span class="text-secondary">[USER]</span>
                </c:otherwise>
            </c:choose>
            <a href="${pageContext.request.contextPath}/Logout" class="btn btn-link btn-sm">Déconnexion</a>
        </div>
    </div>

    <%-- ===== MESSAGES D'ERREUR ===== --%>
    <c:if test="${param.erreur == 'acces_refuse'}">
        <div class="alert alert-danger">Accès refusé : réservé aux administrateurs.</div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <%-- ===== FORMULAIRE AJOUTER / MODIFIER (ADMIN uniquement) ===== --%>
    <c:if test="${sessionScope.role == 'ADMIN'}">
        <div class="border p-3 mb-4">
            <h5>
                <c:choose>
                    <c:when test="${not empty produitEdit}">
                        Modifier le produit #${produitEdit.id}
                    </c:when>
                    <c:otherwise>
                        Ajouter un produit
                    </c:otherwise>
                </c:choose>
            </h5>
            
            <form action="${pageContext.request.contextPath}/${not empty produitEdit ? 'updateProduit' : 'addProduit'}" method="post">
                <input type="hidden" name="idProduit" value="${produitEdit.id}">
                
                <div class="row g-2">
                    <div class="col-md-4">
                        <input type="text" name="nom" class="form-control" placeholder="Nom" value="${produitEdit.nom}" required>
                    </div>
                    <div class="col-md-4">
                        <input type="text" name="description" class="form-control" placeholder="Description" value="${produitEdit.description}" required>
                    </div>
                    <div class="col-md-2">
                        <input type="number" step="0.01" min="0" name="prix" class="form-control" placeholder="Prix" value="${produitEdit.prix}" required>
                    </div>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-primary w-100">
                            <c:choose>
                                <c:when test="${not empty produitEdit}">Confirmer</c:when>
                                <c:otherwise>Ajouter</c:otherwise>
                            </c:choose>
                        </button>
                    </div>
                </div>
            </form>
            
            <c:if test="${not empty produitEdit}">
                <div class="mt-2">
                    <a href="${pageContext.request.contextPath}/listProduits">Annuler</a>
                </div>
            </c:if>
        </div>
    </c:if>

    <%-- ===== BARRE DE RECHERCHE ===== --%>
    <div class="mb-3">
        <form action="${pageContext.request.contextPath}/listProduits" method="get" class="row g-2">
            <div class="col-auto">
                <input type="number" name="idProduit" class="form-control" placeholder="Rechercher par ID">
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-outline-secondary">Rechercher</button>
            </div>
            <div class="col-auto">
                <a href="${pageContext.request.contextPath}/listProduits" class="btn btn-link">Réinitialiser</a>
            </div>
        </form>
    </div>

    <%-- ===== TABLEAU DES PRODUITS ===== --%>
    <table class="table table-bordered">
        <thead class="table-light">
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Description</th>
                <th>Prix</th>
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <th>Actions</th>
                </c:if>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty listeProduits}">
                    <tr>
                        <td colspan="5" class="text-center">Aucun produit trouvé.</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="p" items="${listeProduits}">
                        <tr>
                            <td>${p.id}</td>
                            <td><strong>${p.nom}</strong></td>
                            <td>${p.description}</td>
                            <td>${p.prix} DH</td>
                            <c:if test="${sessionScope.role == 'ADMIN'}">
                                <td>
                                    <a href="${pageContext.request.contextPath}/editProduit?id=${p.id}" class="btn btn-sm btn-outline-warning">Modifier</a>
                                    <a href="${pageContext.request.contextPath}/deleteProduit?id=${p.id}" 
                                       class="btn btn-sm btn-outline-danger"
                                       onclick="return confirm('Supprimer ${p.nom} ?')">Supprimer</a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>

</div>

</body>
</html>