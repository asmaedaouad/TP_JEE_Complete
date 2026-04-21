<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Erreur</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">
<div class="text-center">
    <h1 class="display-1 text-danger">Erreur</h1>
    <p class="lead text-muted">Une erreur est survenue. Veuillez réessayer.</p>
    <a href="${pageContext.request.contextPath}/produit" class="btn btn-dark">Retour à l'accueil</a>
</div>
</body>
</html>