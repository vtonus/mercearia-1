google.load("visualization", "1", {
	packages : [ "corechart" ]
});
// google.setOnLoadCallback(drawChart);
function drawChart() {
	$
			.ajax({
				url : 'Diario',
				data : $("#formulario").serialize(),// {dia:'2014-11-04'},
				type : 'POST',
				success : function(data) {
					$(".diario .dados").show();
					$("#result").html(data);
					var caixa = "<table class='table table-striped responsive-table' >"
							+ "<tr >"
							+ "		<th colspan='2' style='text-align:center; width:100%'>Caixa</th>"
							+ "		</tr>" + "		<tr>" + "	<td>Abriu</td>"
							+ "		<td>R$"
							+ $("#abriu").val()
							+ "</td>"
							+ "		</tr>"
							+ "		<tr>"
							+ "			<td>Fechou</td>"
							+ "			<td>R$"
							+ $("#fechou").val()
							+ "</td>"
							+ "		</tr>"
							+ "		</table>"
							+ "			<table class='table table-striped responsive-table' >"
							+ "		<tr >"
							+ "			<th colspan='2' style='text-align:center; width:100%'>Vendas</th>"
							+ "		</tr>"
							+ "			<tr>"
							+ "			<td>com Cartão</td>"
							+ "			<td>R$"
							+ $("#cartao").val()
							+ "</td>"
							+ "	</tr>"
							+ "			<tr>"
							+ "		<td>com Dinheiro</td>"
							+ "		<td>R$"
							+ $("#dinheiro").val()
							+ "</td>"
							+ "	<tr>"
							+ "		<td>a prazo </td>"
							+ "		<td>R$"
							+ $("#prazo").val()
							+ "</td>"
							+ "	</tr>"
							+ "	</tr>"
							+ "</table>";

					$(".caixa").html(caixa);
					console.log("ma passou aqui hehe");

					// colocar o top produtos
					var topprod = "<table class='table table-striped responsive-table' >"
							+ "<tr >"
							+ "<th colspan='2' style='text-align:center; width:100%'>Top Produtos</th>"
							+ "</tr>";
					var j = 0;
					while ($('#nome' + j).val() != null) {
						
						topprod += "<tr>" + "<td>" + $("#nome" + j).val()
								+ "</td>" + "<td>" + $("#qtd" + j).val()
								+ "</td>" + "</tr>";
						j++;
						if (j==16){
						break;
						}
					}
					topprod += "</table>";
					$(".dados .toprod").html(topprod);

					// fecha o top produtos
					var media = "<table class='table table-striped responsive-table'><tr><th>Media Mensal</th></tr><tr><td>R$"
							+ $("#mmensal").val() + "</td></tr></table>";
					$(".mediamensal").html(media);
					// o outro chart dos valores

					// chart do primeiro
					var data = new google.visualization.DataTable();

					data.addColumn('string', 'Dia');
					data.addColumn('number', 'Valor');
					var i = 0;
					var arrayzao = "[";
					while ($("#venda" + i).val() != '') {
						arrayzao += '["' + $('#dia' + i).val() + '",'
								+ $('#venda' + i).val() + ']';
						i++;
						if ($("#venda" + i).val() != '') {
							arrayzao += ']';
							break;
						} else {
							arrayzao += ',';

						}

					}

					console.log(arrayzao);
					data.addRows(JSON.parse(arrayzao));
					// Create and draw the visualization.
					new google.visualization.AreaChart(document
							.getElementById('chart_div')).draw(data, {});

					// Create and populate the data table Segunda tabela do
					// google chart

					var data2 = new google.visualization.DataTable();

					data2.addColumn('string', 'hora');
					data2.addColumn('number', 'valor');
					var i = 0;
					var array = "[";
					while ($("#valor" + i).val() != '') {
						array += '["' + i + 'H",' + $('#valor' + i).val()
								+ '],';
						i++;
						if (i == 23) {
							break;
						}
					}
					array += '["' + 23 + 'H",' + $('#valor' + 23).val() + ']]';
					console.log(array);
					data2.addRows(JSON.parse(array));
					// Create and draw the visualization.
					new google.visualization.AreaChart(document
							.getElementById('chart_div2')).draw(data2, {});

				}
			});
}

function drawChart2() {
	$.ajax({
		url : "Periodo",
		data : 	 $("#formulario2").serialize(),
		
		type : 'POST',
		success : function(data) {
			
		}
	});
}

function mudaview1() {
	$(".some").hide();
	$(".diario").show();
	console.log("oi");

}

function mudaview2() {
	$(".some").hide();
	$(".periodo").show();
	console.log("oi2");

}