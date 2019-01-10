function validate() {
    var name = document.getElementById("name").value;
    var lastName = document.getElementById("lastname").value;
    var description = document.getElementById("description").value;
    var result = true;
    if (name === "") {
        alert("Enter First Name");
        result = false;
    } else if (lastName === "") {
        alert("Enter Last Name");
        result = false;
    } else if (description === "") {
        alert("Enter description");
        result = false;
    }
    return result;
}

function addRow() {
    if (validate()) {
        var name = document.getElementById("name").value;
        var lastName = document.getElementById("lastname").value;
        var genderObj = document.getElementsByName("gender");
        var description = document.getElementById("description").value;
        var gender;
        if (genderObj[0].checked) {
            gender = "male";
        } else if (genderObj[1].checked) {
            gender = "female";
        }
        $('#table tr:last').after('<tr><td>' + name + '</td><td>' + lastName + '</td><td>' + gender + '</td><td>' + description  +'</td></tr>');

        document.getElementById("myForm").reset();
    }
}

function loadData() {
    if (validate()) {
        var name = document.getElementById("name").value;
        var lastName = document.getElementById("lastname").value;
        var genderObj = document.getElementsByName("gender");
        var description = document.getElementById("description").value;
        var gender;
        if (genderObj[0].checked) {
            gender = "male";
        } else if (genderObj[1].checked) {
            gender = "female";
        }
        var user = {"name": name, "lastName": lastName, "gender": gender, "description": description};
        $.ajax({
            type: "POST",
            url: "jsonServlet.do",
            data: JSON.stringify(user),
            success: parseResponse,
            dataType: "json"
        });
        document.getElementById("myForm").reset();
    }
}
function parseResponse(data) {
    console.log(data);
    var map = JSON.parse(data.responseText);
        $.each(map, function (key, value) {
        $('#table tr:last').after('<tr><td>' + value.name + '</td><td>' + value.lastName + '</td><td>' + value.gender + '</td><td>' + value.description + '</td></tr>');
    });
    console.log(map.size());
}


