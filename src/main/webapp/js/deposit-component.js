$(document).ready(function(){
     $("#header").load("header.html");
});

let accountNumber = localStorage.getItem("ACCOUNTNUMBER");
console.log(accountNumber);
document.querySelector("#accno").value = accountNumber;
function deposit() {
			event.preventDefault();
			// Step 1: Get Form Values
			let deposit = document.querySelector("#depositForm");
			let amount = document.querySelector("#amount").value;
			let accno = document.querySelector("#accno").value;
			let url = "Deposit?accno="+accno+"&amount="+amount;
			 console.log(amount);
				// 2. Check form is valid
				if (deposit.checkValidity()) {
					deposit.classList.remove("was-validated");
					//deposit.submit(); //if valid, then submit the form( which will call the LoginServlet)
					axios.patch(url).then(res=>{
						
						let data=res.data.infoMessage;
						console.log(data);
						window.location.href="summary.jsp?infoMessage="+"DEPOSIT SUCCESS";
					})
					.catch(err=>{
						let data = err.response.data.errorMessage;
				document.querySelector("#message").innerHTML= data;
			});
				} else {
					deposit.classList.add("was-validated");
					//field errors
					if (amount.value.length == 0) {
						amount.classList.add("is-invalid");
					} else {
						amount.classList.add("is-valid");
					}
				} 
		}
	