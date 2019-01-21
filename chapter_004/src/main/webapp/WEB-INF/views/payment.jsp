
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function inputValidate() {
            var username = document.getElementById("username").value;
            var phone = document.getElementById("phone").value;
            if (username === "") {
                alert("Enter name");
                return false;
            } else if (phone === "") {
                alert("Enter phone number");
                return false;
            }
            return true;
        }
    </script>
    <title>Payment</title>
</head>
<body>


<div class="container">
    <div class="row pt-3">
        <h3>
            You choose row : ${seat.row} place:  ${seat.column} , Cost : ${seat.price} ruble
        </h3>
    </div>
    <div class="row">
        <form action="/payment.do" method="post" >
            <div class="form-group">
                <label for="username">First and Last name</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="First and Last name">
            </div>
            <div class="form-group">
                <label for="phone">Telephone number</label>
                <input type="text" class="form-control" id="phone" name="phone" placeholder="Telephone number">
            </div>
            <input type="hidden" name="seatId" value="${seat.id}">
            <button type="submit" class="btn btn-success" onclick="return inputValidate();">Pay</button>
        </form>
    </div>
</div>
</body>
</html>