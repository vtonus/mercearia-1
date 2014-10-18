/*---Funções de compra---*/
var produtoscompra = new Array();
var produtoscontrole = 0;
var produtosdef = new Array();
var totalmax=0;
function chamatelaCompra() {
	$('.content').load('NovaCompra.jsp');
	var iMax = 1000;
	var jMax = 5;

	for (i = 0; i < iMax; i++) {
		produtoscompra[i] = new Array();
		for (j = 0; j < jMax; j++) {
			produtoscompra[i][j] = 0;

		}
	}

	for (i = 0; i < iMax; i++) {
		produtosdef[i] = new Array();
		for (j = 0; j < jMax; j++) {
			produtosdef[i][j] = 0;

		}
	}

}
var teclado;
function juntaCompra() {
	var codigoProduto = $(".cod").val();
	
	$(document).keypress(function(event){
		teclado=event.which; 
	 });
	console.log(teclado);
	 if(teclado=='13'){
	$.ajax({
		url : "BuscaCodigoProduto",
		type : "POST",
		data : {
			produto : $(".cod").val()
		},
		success : function(back) {
			if (back) {
				$('.retornando').html(back);
				//
				$(".desc").val($('#nomeAC').val());
				$(".vlr").val($('#valorAC').val());
				var dados = "<tr>" + "  <th> Descri&ccedil;&atilde;o</th>"
						+ "  <th> QTD</th>" + "  <th> Preco</th>"
						+ "  <th> Sub-total</th>" + "</tr>";
				produtoscontrole++;

				for (i = 0; i < produtoscontrole; i++) {
					if (produtoscompra[i][1] === 0) {
						produtoscompra[i][0] = $('#nomeAC').val();
						produtoscompra[i][1] = $('#valorAC').val();
						produtoscompra[i][2] = $('.qtd').val();
						produtoscompra[i][3] = produtoscompra[i][1]
								* produtoscompra[i][2];
						produtoscompra[i][4] = codigoProduto;
					}
					var impar;
					if (i % 2 != 0) {

						impar = "class='impar'";

					} else
						impar = "";

					dados += "<tr " + impar + " ondblclick=removeproduct(" + i
							+ ")><td> " + produtoscompra[i][0] + "</td>"
							+ "<td> " + produtoscompra[i][2] + "</td>"
							+ "<td> " + produtoscompra[i][1] + "</td>"
							+ "<td> " + produtoscompra[i][3] + "</td></tr>";
					$('.comprado').html(dados);
					// console.log(produtoscontrole);
					// $('.retornando').html("");
					// limpacompras();
				}
				

			}
			var total = 0;
			for (i = 0; i < produtoscontrole; i++) {

				total = produtoscompra[i][3] + total;
				// total= parseFloat( Math.round(total * 100) / 100).toFixed(2)
				// ;
				var valortotalcompra = total.toFixed(2);
				valortotalcompra = valortotalcompra.replace(".",",")
			}
			var tabtotal = "<tr>" + "  <th> Total</th>" + "</tr>" + "<tr>"
					+ "  <td> R$" + valortotalcompra + "</td>" + "</tr>";
			$('.totaltab').html(tabtotal);
			totalmax=valortotalcompra.replace(".",",");
			$("input").val("");
			$(".qtd").val("1");
		}
		
	});// fecha ajax
	/*
	 * $.ajax({ url:"asd", type: "POST", data:{produto: produtoscompra}
	 * 
	 * });
	 */
}
	 }

function limpacompras() {

	$(".desc").val("");
	$(".cod").val("");
	$(".qtd").val("");
	$(".vlr").val("");
	$(".sb").val("");
}
var dados;


function removeproduct(id) {
	console.log('id=');
	console.log(id);

	if (produtoscontrole > 0) {
		produtoscontrole--;

		produtoscompra.splice(id, 1);
		var dados = "<tr>" + "  <th> Descri&ccedil;&atilde;o</th>"
				+ "  <th> QTD</th>" + "  <th> Preco</th>"
				+ "  <th> Sub-total</th>" + "</tr>";

		for (i = 0; i < produtoscontrole; i++) {
			var impar;
			if (i % 2 != 0) {

				impar = "class='impar'";

			} else
				impar = "";
			dados += "<tr " + impar + "ondblclick=removeproduct(" + i
					+ ")><td> " + produtoscompra[i][0] + "</td>" + "<td> "
					+ produtoscompra[i][2] + "</td>" + "<td> "
					+ produtoscompra[i][1] + "</td>" + "<td> "
					+ produtoscompra[i][3] + "</td></tr>";

			// console.log(produtoscontrole);
			// $('.retornando').html("");
			// limpacompras();
		}
		$('.comprado').html(dados);
	} else {
		produtoscompra.splice(id, 1);
		var dados = "<tr>" + "  <th>  Descri&ccedil;&atilde;o</th>"
				+ "  <th> QTD</th>" + "  <th> Preco</th>"
				+ "  <th> Sub-total</th>" + "</tr>";

		$('.comprado').html(dados);
		
	}
	var teste=0
	var total = 0;
	console.log(produtoscontrole);
	for (i = 0; i < produtoscontrole; i++) {
		total = produtoscompra[i][3] + total;
		teste = total.toFixed(2);
	}
	
	
	var tabtotal = "<tr>" + "  <th> Total</th>" + "</tr>" + "<tr>"
			+ "  <td> R$" + teste + "</td>" + "</tr>";
	$('.totaltab').html(tabtotal);
	totalmax=tabtotal;
}




function fazerCompra(){
	
	
	
	//$('perguntafinal')
	
}
function confirmaCompra() {
	produto = new Array();
	qtd = new Array();
	var controleprodutodef = 0;
	var ntem = 0;

	for (i = 0; i < produtoscontrole; i++) {
		console.log("o I tá valendo " + i)
		if (controleprodutodef == 0) {
			produtosdef[0][0] = produtoscompra[i][0];
			produtosdef[0][1] = produtoscompra[i][1];
			produtosdef[0][2] = produtoscompra[i][2];
			produtosdef[0][3] = produtoscompra[i][3];
			produtosdef[0][4] = produtoscompra[i][4];
			controleprodutodef++;
			console.log('passou por aqui primeiro');
		} else {
			for (q = 0; q < controleprodutodef; q++) {

				if (produtoscompra[i][4] == produtosdef[q][4]) {
					produtosdef[q][2] = parseInt(produtoscompra[i][2])
							+ parseInt(produtosdef[q][2]);
					console.log('entrou aki com' + produtoscompra[i][4]
							+ "é igual ao" + produtosdef[q][4]);
					ntem = 1;
					break;

				}
			}
			if (ntem == 0) {
				produtosdef[q + 1][0] = produtoscompra[i][0];
				produtosdef[q + 1][1] = produtoscompra[i][1];
				produtosdef[q + 1][2] = produtoscompra[i][2];
				produtosdef[q + 1][3] = produtoscompra[i][3];
				produtosdef[q + 1][4] = produtoscompra[i][4];
				// console.log('passou por aqui' + controleprodutodef);
				controleprodutodef++;
			}
			ntem = 0;
		}

	}
	console.log($( "input:checked" ).val());
	// var jsonString = JSON.stringify(produtosdef);
	var jsonString = JSON.stringify(produtoscompra);
	$.ajax({
		url : "NovaCompra",
		type : "POST",
		data : {
			produto : jsonString,
		    troco: $("#trocado").val(),
		    desc:  $("#descount").val(),
		    pago:  $("#pagado").val(),
		    metodoPag: metododepag,
			
		},
		success : function(back) {
			mensagem("green","Compra foi confirmada!!");
			cancelaCompra();
			cancelapergunta();
			
		},
		error : function(back){
			mensagem("red","Erro, contate seu administrador!");	
			cancelaCompra();
			
		}

	});

}

function cancelaCompra() {
	var iMax = 1000;
	var jMax = 5;

	for (i = 0; i < iMax; i++) {
		produtoscompra[i] = new Array();
		for (j = 0; j < jMax; j++) {
			produtoscompra[i][j] = 0;

		}
	}
	produtoscontrole = 0;
	dados = "<tr>" + "  <th>  Descri&ccedil;&atilde;o</th>" + "  <th> QTD</th>"
			+ "  <th> Preco</th>" + "  <th> Sub-total</th>" + "</tr>";
	$('.comprado').html('');
	limpacompras();
	$('.totaltab').html('');
}

/*---Procura compra---*/

function buscaDadosCompra() {

	$.ajax({
				url : "BuscaCompra",
				data : {
					id : $('#codigo').val(),
					dataHoraIni : $('#dtini').val(),
					dataHoraFim : $('#dtfin').val(),
					cliente : $('#cliente').val(),
					funcionario : $('#func').val()
				},
				type : 'POST',
				success : function(data) {
					$('#result').html(data);
					var i = 0;
					var dados = "<table class='tabretorno'>"
							+ "<tr><th>ID</th>" + "<th>Cliente</th>"
							+ "<th>Funcionario</th>" + "<th>Data e Hora</th>"
							+ "<th>Valor</th>" + "<th>Detalhes</th>"
							+ "<th>Excluir</th></tr>";
					while ($("#id" + i).val() != null) {
						dados += "" + "<tr> " + "<td><span id='tid"
								+ i
								+ "'>"
								+ $("#id" + i).val()
								+ "</span><input  type='text' class='nome"
								+ i
								+ "' value='"
								+ $("#id" + i).val()
								+ "'></input></td>"
								+ "<td><span id='tcliente"
								+ i
								+ "'>"
								+ $("#cliente" + i).val()
								+ " </span><input  type='text' class='telefone"
								+ i
								+ "' value='"
								+ $("#cliente" + i).val()
								+ "'></input></td>"
								+ "<td><span id='tfuncionario"
								+ i
								+ "'>"
								+ $("#funcionario" + i).val()
								+ "</span><input  type='text' class='doc"
								+ i
								+ "' value='"
								+ $("#funcionario" + i).val()
								+ "'></input></td>"
								+ "<td><span id='tdata"
								+ i
								+ "'>"
								+ $("#datahora" + i).val()
								+ "</span><input  type='text' class='email"
								+ i
								+ "' value='"
								+ $("#datahora" + i).val()
								+ "'></input></td>"
								+ "<td><span id='tvalor"
								+ i
								+ "'>"
								+ $("#valor" + i).val()
								+ "</span><input  type='text' class='data"
								+ i
								+ "' value='"
								+ $("#valor" + i).val()
								+ "'></input></td>"
								+ "<td><img id='tbusca"	+ i
								+ "' onclick='BuscaDetalhesCompra("
								+ $("#id" + i).val()
								+ ")' src='../images/plus.png' /><img id='salva"
								+ i
								+ "' onclick='pEditaCompra("
								+ i
								+ ")' style='display:none' src='../images/salva.png' /><img id='salva"
								+ i
								+ "' onclick='cEditaCompra("
								+ i
								+ ")' style='display:none' src='../images/close15.png' /></td>"
								+ "<td><img onclick='pExcluiCompra(" + i
								+ ")' src='../images/exclui.png' /></td>"
								+ "</tr>";
						i++;

					}
					dados += "</table>";

					$('#tabdados').html(dados);
				}
			});
}

function BuscaDetalhesCompra(id) {
	$.ajax({
		url : "BuscaDetalhesCompra",
		data : {
			id : id
		},
		type : 'POST',
	});

}


function formatReal( int )
{
        var tmp = int+'';
        tmp = tmp.replace(/([0-9]{2})$/g, ",$1");
        if( tmp.length > 6 )
                tmp = tmp.replace(/([0-9]{3}),([0-9]{2}$)/g, ".$1,$2");
 
        return tmp;
}

function mascara(este){
	var teste=$(este).val().replace(",","");
	teste=teste.replace(".","");
	$(este).val(formatReal(teste));
	
}
function trocando(este){
	var testetroca=$("#trocado").val().replace(",","");
	testetroca=testetroca.replace(".","");
	$("#trocado").val(formatReal(testetroca).replace(".",""));
	
}

function calculo(){
	
	var pagado=$("#pagado").val().replace(",","");
	pagado=pagado.replace(".","");
	var descount=$("#descount").val().replace(",","");
	descount=descount.replace(".","");
	var total=$("#total").val().replace(",","");
	total=total.replace(".","");
	var trocado=$("#trocado").val().replace(",","");
	trocado=trocado.replace(".","");
	
	var totalpa=total-descount;
	var troco=pagado-totalpa;
	$("#trocado").val(troco);
	trocando();
	
	
}


function pagamentoCompra(){
	$(".fundoq").show();
	$(".questao").show();
	console.log(totalmax);
	$("#trocado").val("0,00");
	$("#descount").val("0,00");
	$("#pagado").val(totalmax);	
	$("#total").val(totalmax);
	
	
}

function cancelapergunta(){
	$(".fundoq").hide();
	$(".questao").hide();
	console.log(totalmax);
	$("#trocado").val("0,00");
	$("#descount").val("0,00");
	$("#pagado").val("0,00");	
	
}
var metododepag=1;
function metodopag(pag){
	metododepag=pag;
	
}