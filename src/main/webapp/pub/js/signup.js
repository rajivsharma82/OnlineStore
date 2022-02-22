const form = document.getElementById('form');
const password1El =document.getElementById('password1');
const password2El =document.getElementById('password2');
const messageContainer =document.querySelector('.message-container');
const message = document.getElementById('message');

// Event Listner

let isValid = false;
let passwordMatch = false;


function validateForm(){
    // use constraint API
    isValid = form.checkValidity();
    // console.log(isValid);
    if(!isValid){
        message.textContent="Please fill out all fields.";
        message.style.color = 'red';
        messageContainer.style.borderColor = 'red';
    }
    if(password1El.value === password2El.value){
        passwordMatch=true;
        password1El.style.borderColor = "green";
        password2El.style.borderColor="green";

    } else{
        passwordMatch = false;
        password1El.style.borderColor = "red";
        password2El.style.borderColor="red";
        message.style.color ="red";
        message.textContent= "Make sure password match.";
        messageContainer.style.borderColor="red";
        return;
    }

    // if form is valid
    if(isValid && passwordMatch){
        message.textContent = "Successfully Registered!!!"
        message.style.color = "green";
        messageContainer.style.color = "green";
    }
}
//
// function storeFormData(){
//     const user = {
//         name: form.name.value,
//         phone:form.phone.value,
//         email: form.email.value,
//         website: form.website.value,
//         password: form.password.value
//     };
//     console.log(user);
// }

function processFormData(e) {
    //e.preventDefault();
    // Validate Form
    //console.log("hello");
    // Submit Form if Valid
    validateForm();
    //submit data if valid
    if(isValid && passwordMatch){
        return true;
        //storeFormData();
        // var url= "store.html";
        // window.location = url;
        //window.location.href="index.html?userid=" + form.name.value;

        // return true ... comment line 67 .. else return false (comment 57 need some testing )
    }
}

// Event Listener
document.getElementById('form').addEventListener('submit', processFormData);



