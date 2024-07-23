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
        <link rel="icon" type="image/x-icon" href="assets/logo/logo.jpg">
        <title>500</title>
    </head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <body class="d-flex flex-column min-vh-100">
        <section class="py-3 py-md-5 min-vh-100 d-flex justify-content-center align-items-center">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="text-center">
                            <h2 class="d-flex justify-content-center align-items-center gap-2 mb-4">
                                <span class="display-1 fw-bold">5</span>
                                <i class="bi bi-exclamation-circle-fill text-danger display-4"></i>
                                <span class="display-1 fw-bold bsb-flip-h">0</span>
                            </h2>
                            <h3 class="h2 mb-2">Oops! You're lost.</h3>
                            <p class="mb-5">Server error.</p>
                            <a class="btn bsb-btn-5xl btn-dark rounded-pill px-5 fs-6 m-0" href="${pageContext.request.contextPath}/home" role="button">Back to Home</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="https://unpkg.com/bs-brain@2.0.4/components/error-404s/error-404-1/assets/css/error-404-1.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>

</html>
