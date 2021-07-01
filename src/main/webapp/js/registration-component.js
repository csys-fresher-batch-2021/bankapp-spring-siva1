$(document).ready(function(){
     $("#header").load("header.html");
});
function register() {
			event.preventDefault();
			// Step 1: Get Form Values
			let regiterForm = document.querySelector("#registerForm");
			let username = document.querySelector("#username").value;
			let password = document.querySelector("#password").value;
			let emailId = document.querySelector("#email").value;
			let mobileNo = document.querySelector("#number").value;
			let address = document.querySelector("#address").value;
			let amount = document.querySelector("#amount").value;
			let url = "Register";
			let user={
					
					"name":username,
					"email":emailId,
					"password":password,
					"mobileNo":mobileNo,
					"address":address,
					"balance":amount
			}
			console.log(user);
			 
				// 2. Check form is valid
				if (registerForm.checkValidity()) {
					regiterForm.classList.remove("was-validated");
					axios.post(url,user).then(res=>{
						let data=res.data.infoMessage;
						console.log(data);
						alert(data);
						window.location.href="login.html";
					})
					.catch(err=>{
						let data = err.response.data.errorMessage;
						console.log(data);
				document.querySelector("#message").innerHTML= data;
			});
				} else {
					registerForm.classList.add("was-validated"); 
					//field errors
					if (username.value.length == 0) {
						username.classList.add("is-invalid");
					}
					else{
						username.classList.add("is-valid");
					}
				} 
			
			
		}