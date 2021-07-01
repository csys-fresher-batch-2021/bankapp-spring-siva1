$(document).ready(function(){
     $("#header").load("header.html");
});
function adminLogin() {
			event.preventDefault();
			let loginForm = document.querySelector("#loginForm");
			let username = document.querySelector("#userName").value;
			let password = document.querySelector("#password").value;
			let url = "AdminLogin" ;
			let user = {
					"name":username,
					"password":password
					
			};
			 //Step 1: Get Form Values
			console.log(username + "-" + password);
			// 2. Check form is valid
			if (loginForm.checkValidity()) {
				loginForm.classList.remove("was-validated");
				
				axios.post(url,user).then(res=>{
					 let name = JSON.stringify(res.data.name);
					 alert(name);
					 localStorage.setItem("LOGGED_IN_USER",name);
					alert("Login Success");
					window.location.href="adminDisplay.html";
				 })	
					.catch(err=>{
						let data = err.response.data.errorMessage;
				document.querySelector("#message").innerHTML= data;
			});
		
			} else {
				loginForm.classList.add("was-validated");
				//field errors
				if (username.value.length == 0) {
					username.classList.add("is-invalid");
				} else {
					username.classList.add("is-valid");
				}
			} 
			 
			
			
		}