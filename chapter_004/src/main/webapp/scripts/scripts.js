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