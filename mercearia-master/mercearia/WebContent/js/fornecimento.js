/*var dados="" +
		"<tr>"+
			"<th>Código</th>"+
			"<th>Produto</th>"+
			"<th>Unid.</th>"+
			"<th>Qtd.</th>"+
		+"</tr>";*/
var dados;
function adcProdutoForn() {
	var i;
	var impar;
	
	dados += "<tr " + impar + " ondblclick=removeproduct(" + i
							+ ")><td> " + $("#cod").val() + "</td>"
							+ "<td> " + $("#prod").val() + "</td>"
							+ "<td> " + $("#unid").val() + "</td>"
							+ "<td> " +$("#qtd").val() + "</td>" +
							"<td> " +$("#vlr").val() + "</td>" +
									"</tr>";
					$('.tabelaForn').html(dados);
}
