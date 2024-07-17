<%-- 
    Document   : home
    Created on : Jul 7, 2024, 9:18:47 PM
    Author     : MinhChien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thông tin tài khoản</title>
    </head>
    <style>
        .profile-image {
            width: 400px;
            height: 400px;
            border-radius: 50%;
            object-fit: cover;
            margin: 0 auto;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <body class="d-flex flex-column min-vh-100">
        <jsp:include page="_header.jsp"></jsp:include>
            <main class="flex-fill" style="padding-top: 56px; background-color: #f5f5f5">
            <div class="container" style="margin-top: 50px">
                NOTFOUND
            </div>
        </main>
        <jsp:include page="_footer.jsp"></jsp:include>
        
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script>
                                    debugger;
                                    $(document).ready(function () {
                                        $('[data-toggle="tooltip"]').tooltip();
                                    });

                                    setTimeout(function () {
                                        // Select all alerts with the 'alert' class
                                        var alerts = document.querySelectorAll('.alert');
                                        alerts.forEach(function (alert) {
                                            // Hide each alert
                                            $(alert).alert('close');
                                        });
                                    }, 2000);
                                    //
                                    function editProfile(username, fullName, email, dob, sex, address) {
                                        document.getElementById('editUsername').value = username;
                                        document.getElementById('editFullName').value = fullName;
                                        document.getElementById('editEmail').value = email;
                                        document.getElementById('editDOB').value = dob;
                                        document.getElementById('editSex').value = sex;
                                        document.getElementById('editAddress').value = address;
                                    }

                                    function changeAvatar(username) {
                                        document.getElementById('changeUsername').value = username;
                                    }
        </script>
    </body>

</html>
