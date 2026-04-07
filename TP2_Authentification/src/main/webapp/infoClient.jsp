<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Client" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Clients</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<div class="container py-5">

    <!-- Message -->
    <% if(request.getAttribute("success") != null) { %>
        <div class="alert alert-success text-center">
            <%= request.getAttribute("success") %>
        </div>
    <% } %>

    <div class="card border-0 shadow-sm">
        <div class="card-body">

            <h5 class="mb-4 text-center">Liste des clients</h5>

            <div class="table-responsive">
                <table class="table table-sm align-middle">
                    <thead class="table-light">
                        <tr>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Téléphone</th>
                            <th>Email</th>
                        </tr>
                    </thead>

                    <tbody>
                        <%
                            List<Client> clients = (List<Client>) request.getAttribute("clients");

                            if(clients != null && !clients.isEmpty()){
                                for(Client c : clients){
                        %>
                        <tr>
                            <td><%= c.getNom() %></td>
                            <td><%= c.getPrenom() %></td>
                            <td><%= c.getTelephone() %></td>
                            <td><%= c.getEmail() %></td>
                        </tr>
                        <%
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="4" class="text-center text-muted">
                                Aucun client
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

                <a href="Inscription.jsp" class="btn btn-dark">
                    Ajouter client
                </a>
            </div>

        </div>
    </div>

</div>

</body>
</html>