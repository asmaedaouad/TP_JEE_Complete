<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Menu Principal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="d-flex justify-content-center align-items-center vh-100 bg-light">

<div class="card shadow-sm border-0 text-center" style="width: 400px;">
    <div class="card-body p-4">

        <h3 class="mb-3">Dashboard</h3>
        <p class="text-muted mb-4">Gestion des clients et réservations</p>

        <div class="d-grid gap-3">
            <a href="Inscription.jsp" class="btn btn-primary rounded-pill">
                Ajouter Client
            </a>

            <a href="Reservation.jsp" class="btn btn-success rounded-pill">
                Ajouter Réservation
            </a>
        </div>

    </div>
</div>

</body>
</html>