$(document).ready(function() {
	$("#header").load("header.html");
});
let accountNumber = localStorage.getItem("ACCOUNTNUMBER");
console.log(accountNumber);
document.querySelector("#accno1").value = accountNumber;
function transfer() {
	event.preventDefault();
	// Step 1: Get Form Values
	let transfer = document.querySelector("#transferForm");
	let amount = document.querySelector("#amount").value;
	let accnoOne = document.querySelector("#accno1").value;
	let accnoTwo = document.querySelector("#accno2").value;
	let url = "Transfer?fromAccNo=" + accnoOne + "&toAccNo=" + accnoTwo + "&amount=" + amount;
	console.log(amount);
	// 2. Check form is valid
	if (transfer.checkValidity()) {
		transfer.classList.remove("was-validated");
		axios.get(url).then(res => {
			let data = res.data.infoMessage;
			window.location.href = "summary.jsp?infoMessage=" + "TRANSFER SUCCESS";
		})
			.catch(err => {
				let data = err.response.data.errorMessage;
				document.querySelector("#message").innerHTML = data;

			});
	} else {
		transfer.classList.add("was-validated");
		//field errors
		if (amount.value.length == 0) {
			amount.classList.add("is-invalid");
		} else {
			amount.classList.add("is-valid");
		}
	}


}