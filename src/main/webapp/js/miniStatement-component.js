$(document).ready(function() {
	$("#header").load("header.html");
});
function getMiniStatement() {
	let accNo = localStorage.getItem("ACCOUNTNUMBER");
	let url = "MiniStatement?accno=" + accNo;
	fetch(url).then(res => res.json()).then(res => {

		let transaction = res;
		console.log(transaction);
		let statement = "";
		if (transaction == 0) {
			statement += "<tr><td colspan=14 class='text-center'>" +
				"No Transactions Yet" + "</td></tr>";
		}

		for (let transfer of transaction) {
			statement += "<tr><td>" + transfer.user.name + "</td>" +
				"<td>" + transfer.user.accNo + "</td>" +
				"<td>" + transfer.transactionType + "</td>" +
				"<td>" + transfer.amount + "</td>" +
				"<td>" + transfer.comments + "</td>" +
				"<td>" + transfer.transactionDateTime + "</td></tr>";

		}
		document.querySelector("#ministatement").innerHTML = statement;

	})
}
getMiniStatement();