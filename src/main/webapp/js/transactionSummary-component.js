$(document).ready(function() {
	$("#header").load("header.html");
});
function filter() {
	event.preventDefault();
	let accNo = localStorage.getItem("ACCOUNTNUMBER");
	let url = "Statement?accno=" + accNo;
	fetch(url).then(res => res.json()).then(res => {
		let transactionType = document.querySelector("#type").value;
		console.log(transactionType);
		let transaction = res;
		if (transactionType != null) {
			transaction = res.filter(res => res.transactionType.toLowerCase().includes(transactionType.toLowerCase()));
		}
		getTransactionDetails(transaction);
	})


}

function getTransactionDetails(statement) {
	let transfer = statement;
	console.log(transfer);
	let summary = "";
	if (transfer == 0) {
		summary += "<tr><td colspan=14 class='text-center'>" +
			"No Transactions Yet" + "</td></tr>";
	}
	for (let transaction of transfer) {
		summary += "<tr><td>" + transaction.user.name + "</td>" +
			"<td>" + transaction.user.accNo + "</td>" +
			"<td>" + transaction.transactionType + "</td>" +
			"<td>" + transaction.amount + "</td>" +
			"<td>" + transaction.comments + "</td>" +
			"<td>" + transaction.transactionDateTime + "</td></tr>";

	}
	document.querySelector("#transactionlist").innerHTML = summary;
}
