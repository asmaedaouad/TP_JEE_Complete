<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Reservation" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Réservations</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<div class="container py-5">

    
    <% if(request.getAttribute("success") != null) { %>
        <div class="alert alert-success text-center">
            <%= request.getAttribute("success") %>
        </div>
    <% } %>

    <div class="card border-0 shadow-sm">
        <div class="card-body">

            <h5 class="mb-4 text-center">Liste des réservations</h5>

            <div class="table-responsive">
                <table class="table table-sm align-middle">
                    <thead class="table-light">
                        <tr>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Téléphone</th>
                            <th>Email</th>
                            <th>Type</th>
                            <th>Prix</th>
                            <th>Vue</th>
                        </tr>
                    </thead>

                    <tbody>
                        <%
                            List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");

                            if(reservations != null && !reservations.isEmpty()){
                                for(Reservation r : reservations){
                        %>
                        <tr>
                            <td><%= r.getClient().getNom() %></td>
                            <td><%= r.getClient().getPrenom() %></td>
                            <td><%= r.getClient().getTelephone() %></td>
                            <td><%= r.getClient().getEmail() %></td>
                            <td><%= r.getType() %></td>
                            <td><%= r.getPrix() %></td>
                            <td><%= r.getVue() %></td>
                        </tr>
                        <%
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="7" class="text-center text-muted">
                                Aucune réservation
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>

            
            <div class="d-flex justify-content-between mt-3">
                <a href="index.jsp" class="btn btn-outline-secondary">
                    Menu principal
                </a>

                <a href="Reservation.jsp" class="btn btn-dark">
                    Ajouter réservation
                </a>
            </div>

        </div>
    </div>

</div>

</body>
</html>