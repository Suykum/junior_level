
function validateUserInput() {
    var name = document.getElementById("name").value;
    var login = document.getElementById("login").value;
    var email = document.getElementById("email").value;
    var pass = document.getElementById("pass").value;
    var country = document.getElementById("country").value;
    var city = document.getElementById("city").value;
    var role = document.getElementById("role").value;
    if (name === "") {
        alert("Enter User name");
        return false;
    } else if (login === "") {
        alert("Enter login name");
        return false;
    } else if (email === "") {
        alert("Enter email address");
        return false;
    } else if (pass === "") {
        alert("Enter password");
        return false;
    } else if (country === "") {
        alert("Enter user's country");
        return false;
    } else if (city === "") {
        alert("Enter user's city");
        return false;
    }
    else if (role === "") {
        alert("Select a Role");
        return false;
    }
    return true;
}
function getCountry() {
    $.ajax({
        type: "GET",
        url: "CountryCity.do",
        success: function (data) {
            console.log(data);
            $("#country option").not(":first").remove();
            $.each(data, function (indexInArray, value) {
                $('#country option:last').after('<option>' + value + '</option>');
            });

        }
    });
}
function addCountry() {
    var newCountry = document.getElementById("newCountry").value;
    $('#country option:last').after('<option>' +newCountry + '</option>');
   document.getElementById("newCountry").value = "";
}

function getCity(country) {
    $.ajax({
        type: "POST",
        url: "CountryCity.do",
        data:{country:country},
        success: function (data) {
            console.log(data);
            $("#city option").not(":first").remove();
            $.each(data, function (indexInArray, value) {
                $('#city option:last').after('<option>' + value + '</option>');
            });
        }
    });
}
function addCity() {
    var newCity = document.getElementById("newCity").value;
    $('#city option:last').after('<option>' +newCity + '</option>');
    document.getElementById("newCity").value = "";
}
