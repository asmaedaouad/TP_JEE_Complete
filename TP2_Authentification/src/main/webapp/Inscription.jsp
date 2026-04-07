<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Inscription Client</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="d-flex justify-content-center align-items-center vh-100 bg-light">

<div class="card shadow-sm border-0" style="width: 420px;">
    <div class="card-body p-4">

        <h3 class="text-center mb-2">Inscription</h3>
        <p class="text-center text-muted mb-4">Ajouter un client</p>

        <% if(request.getAttribute("error") != null) { %>
            <div class="alert alert-danger text-center">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>

        <form method="post" action="CreerClientServlet">

            <div class="mb-3">
                <input type="text" name="nom" class="form-control" placeholder="Nom" >
            </div>

            <div class="mb-3">
                <input type="text" name="prenom" class="form-control" placeholder="Prénom" >
            </div>

            <div class="mb-3">
                <input type="text" name="telephone" class="form-control" placeholder="Téléphone" >
            </div>

            <div class="mb-3">
                <input type="email" name="email" class="form-control" placeholder="Email" >
            </div>

            <button type="submit" class="btn btn-success w-100 rounded-pill">
                Ajouter
            </button>
        </form>

        <div class="mt-3 text-center">
            <a href="index.jsp" class="text-decoration-none"> Retour</a>
        </div>

    </div>
</div>

</body>
</html>