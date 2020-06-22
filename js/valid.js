function checkBooking(form)
{
    //using characters to test whether the domain exists
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    var letters = /^[A-Za-z ]/;

    //checks the textboxes
    while(form.username.value == "" || form.email.value == "" ||  form.security_code.value == "")
    {
        alert("Please, Fill In All Required Information In The Textboxes");
        return false;
    }

    //checks if there is a valid email
    while(mailformat.test(form.email.value) == false)
    {
        alert('Invalid email address');
        return false;
    }

    // check if userID only contains letters
    while(letters.test(form.username.value) == false)
    {
        alert('Use letters only');
        return false;
    }

    // checks if code matches input
    while (!(form.code.value == form.security_code.value))
    {
        alert('Invalid Security Code');
        return false;
    }

    // checks if information can be edited
    while(!(form.editusername.value == form.username.value))
    {
        alert('Invalid Username');
        return false;
    }
}