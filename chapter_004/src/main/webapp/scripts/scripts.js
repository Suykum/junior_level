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
        $('#table tr:last').after('<tr><td>' + name + '</td><td>' + lastName + '</td><td>' + gender + '</td><td>' + description  +'</td></tr>')

        document.getElementById("myForm").reset();
    }
}


