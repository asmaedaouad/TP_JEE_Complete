<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">

<div class="card shadow-sm border-0" style="width: 400px;">
    <div class="card-body p-4">
        <h3 class="text-center mb-2">Connexion</h3>
        <p class="text-center text-muted mb-4">Veuillez entrer vos identifiants</p>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <c:if test="${not empty success}">
            <div class="alert alert-success">${success}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/produit" method="post">
            <input type="hidden" name="action" value="login">
            <div class="mb-3">
                <input type="text" name="username" class="form-control" placeholder="Nom d'utilisateur" required>
            </div>
            <div class="mb-3">
                <input type="password" name="password" class="form-control" placeholder="Mot de passe" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Se connecter</button>
        </form>

        <p class="mt-3 text-center">
            Pas encore de compte ?
            <a href="${pageContext.request.contextPath}/produit?action=register">S'inscrire</a>
        </p>
    </div>
</div>

</body>
</html>