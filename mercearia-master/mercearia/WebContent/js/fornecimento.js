var dadosforn;
var contrforn=0;
function remeped(id){
	$('#tr'+id).remove();
	console.log(id);
	dadosforn=$('.tabelaForn').html();
	//contrforn--;
}


function adcProdutoForn() {
	var valida=0;
	if($('#cod').val()==''){
		valida=1;
	}
	if($('#prod').val()==''){
		valida=1;
	}
	if($('#unid').val()==''){
		valida=1;
	}
	
	if(valida==1){	
		mensagem("Red", "preencha os campos obrigatorios!");
	}else{
			
		if($('#qtd').val()=='' || $('#qtd').val()=='0' ){
			$('#qtd').val('1');
		}
		
		
	
	var impar="";

	dadosforn += "<tr id='tr"+contrforn+"'" + impar + " ondblclick=removeproduct(" + contrforn + ")><td> "
			+ "<input readonly id='cod"+contrforn+"' type='text' style='width:100%' value='"+$('#cod').val()+"'/></td><td> <input style='width:100%' readonly id='prod"+contrforn+"' type='text' value='"+$('#prod').val()+"'/></td>"
			+ "<td> <input readonly id='vlrud"+contrforn+"' type='text' style='width:100%' value='"+$('#unid').val()+"'/></td>" + "<td><input readonly style='width:100%' id='qtd"+contrforn+"' type='text' value='"+$('#qtd').val()+"'/> </td>" + "<td><input readonly style='width:100%' id='cod"+contrforn+"' type='text' value='"+$('#vlr').val()+"'/>" +
					"</td><td onclick='remeped("+contrforn+")'><span class='glyphicon glyphicon-remove' style='cursor:pointer' ></span></td></tr>";
	$('.tabelaForn').html(dadosforn);
	contrforn++;
	total();
	}
	
	

		
}

function procuraNomedoFornecedor() {
	if($('#fornnome').val()==''){
		$("#resultForne").html("");
		
		
	}else{
	$.ajax({
				url : 'BuscaNomeFornecedor',
				data : {
					nome : $('#fornnome').val()
				},
				type : 'POST',
				success : function(back) {
					$("#resultados").html(back);
					var result = "<table style='width:100%; border:0; cursor:pointer' cellspacing='0px'>"
					var i = 0;
					var impar;

					while ($("#nome" + i).val() != null) {
						if (i % 2 != 0) {
							impar = "style='background-color:#ddd'";
						} else {
							impar = "style='background-color:#ccc'";
						}
						result += "<tr onclick='enviacodigo("+i+")' " + impar + ">";

						result += "<td>" + $("#nome" + i).val() + "</td><td>"
								+ $("#cpf" + i).val() + "</td>";
						result += "</tr>";

						i++;
					}
					
					result += "</table>";
					$("#resultForne").html(result);
				},
				error : function() {
					$("#resultForne").html("Nenhum Fornecedor encontrado");
				}
			});

}}
var idfornescolhido=0;
function enviacodigo(i){
	$("#fornnome").val($("#cpf" + i).val());
	idfornescolhido=$("#nome" + i).val();
	$("#resultForne").html("");
	
}

function total(){
	var produtoped = new Array();
	var iMax = 1000;
	var jMax = 3;
var i=0;
var j=0;
var p=0;
var soma=0;
	for (i = 0; i < iMax; i++) {
		produtoped[i] = new Array();
		for (j = 0; j < jMax; j++) {
			produtoped[i][j] = 0;
		}
	}
	
	
	for(i=0;i<contrforn;i++){
		var $object=$("#tr"+i);
		if($object.length){
			soma = soma + parseFloat($("#qtd"+i).val());
			p++;
			
		}
		
	}
	console.log(soma);
	$("#totaltabela").html(String(soma));
	
}


function confirmaPedido(){
	var produtoped = new Array();
	var iMax = 1000;
	var jMax = 3;
var i=0;
var j=0;
var p=0;
	for (i = 0; i < iMax; i++) {
		produtoped[i] = new Array();
		for (j = 0; j < jMax; j++) {
			produtoped[i][j] = 0;
		}
	}
	
	
	for(i=0;i<contrforn;i++){
		var $object=$("#tr"+i);
		if($object.length){
			produtoped[p][0]=$("#cod"+i).val();
			produtoped[p][1]=$("#qtd"+i).val();
			produtoped[p][2]=$("#vlrud"+i).val();
			p++;
			
		}
		
	}
	
	var jsonString = JSON.stringify(produtoped);
	$.ajax({
		url : "NovoPedido",
		type : "POST",
		data : {
			produto : jsonString,
			idForn:idfornescolhido,
			desc:$('#comentarioforn').val(),
		},
		success : function(back) {
			mensagem("green","Pedido foi confirmado!!");
			cancelaCompra();
			
		},
		error : function(back) {
			mensagem("red","Erro, contate seu administrador!");	
			cancelaCompra();
			
		}

	});
	
	
	
}



function buscacodigoforn(){

	$(document).keypress(function(event){
	teclado=event.which; 
 });
	console.log(teclado);
	 if(teclado=='13'){
    var codigoProduto = $("#cod").val();
	$.ajax({
		url : "BuscaCodigoProduto",
		type : "POST",
		data : {
			produto : codigoProduto
		},
		success : function(back) {
			if (back) {
				$('.retornando').html(back);
				//
				$("#prod").val($('#nomeAC').val());
				//$("#unid").val($('#valorAC').val());
				//$('#qtd').focus();
				$("#unid").focus();
			}
		   }
		});

	 }
}


function adcProdutoFornqtd(){
	$(document).keypress(function(event){
		teclado=event.which; 
	 });
	console.log(teclado);
	var unidade=$('#unid').val();
	var qtd = $('#qtd').val();
	var total= unidade * qtd;
	var valortotalcompra = total.toFixed(2);
    valortotalcompra = valortotalcompra.replace(",",".");
	$('#vlr').val(valortotalcompra);
	
	 if(teclado=='13'){
	//adcProdutoForn();
	}
	
	
}


function BuscaPedido(){
	$.ajax({
		url:"BuscaPedido",
		data:{
			id : $('#codigo').val(),
		
			dataMin : $('#dtini').val(),
			dataMax : $('#dtfin').val(),
			forn : $('#forn').val(),
			func : $('#func').val(),
			Produto: '',
		},
		type: 'POST',
		success: function(data){
			$('#result').html(data);
			var i = 0;
			var dados = "<div id='collapse4' class='body'><table id='dataTable' class='table table-bordered table-condensed table-hover table-striped' >"
				+ "<thead><tr><th>ID</th>" + "<th>Fornecedor</th>"
					+ "<th>Funcionario</th>" + "<th>Data e Hora</th>"
					+ "<th>Valor</th>" + "<th>Detalhes</th>"
					+ "<th>Excluir</th></tr></thead><tbody>";
			while ($("#id" + i).val() != null) {
				if(i%2!=0){
					 impar="class='impar'";
					
				}else{
					 impar="";
				}
				
				dados += "" + "<tr "+impar+"> " + "<td><span id='tid"
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
						+ $("#fornecedor" + i).val()
						+ " </span><input  type='text' class='telefone"
						+ i
						+ "' value='"
						+ $("#fornecedor" + i).val()
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
						+ $("#valorTotal" + i).val()
						+ "</span><input  type='text' class='data"
						+ i
						+ "' value='"
						+ $("#valorTotal" + i).val()
						+ "'></input></td>"
						+ "<td><span class='clickable glyphicon glyphicon-plus' id='tbusca"
						+ i
						+ "' onclick='BuscaDetalhesPedido("
						+ $("#id" + i).val()
						+ ")' ><span> <img id='salva"
						+ i
						+ "' onclick='pEditaCompra("
						+ i
						+ ")' style='display:none' src='../images/salva.png' /><img id='salva"
						+ i
						+ "' onclick='cEditaCompra("
						+ i
						+ ")' style='display:none' src='../images/close15.png' /></td>"
						+ "<td><span class='clickable glyphicon glyphicon-remove' onclick='pExcluiPedido(" + i
						+ ")' ></span></td>"
						+ "</tr>";
				i++;

			}
			dados += "</tbody></table></div>"+
			" <script>"+
" $(b()); function b() {" +
"   Metis.MetisTable();"+
"   Metis.metisSortable();"+
"  }"+
" </script>";

			$('#tabdados').html(dados);
		
		}
	
	
	});
}


function pExcluiPedido(pk){
	var pergunta=	"<span style='width: 589px;    font-size: 16px;    font-weight: bold;'"+
	">Voc&ecirc; realmente quer Excluir: Pedido<br>" + $('#id'+pk).val()+"?</span>"+
				 " <div class='yes' onclick='excluiPedido("+pk+")'>Sim</div> <div class='nope' onclick='cExcluirCompra("+pk+")'>N&atilde;o</div>";
		$('#procli .fundoq').show();
		$("#procli .question").show();
		$("#procli .question").html(pergunta);

}
function BuscaDetalhesPedido(id) {
	$.ajax({
		url : "BuscaDetalhesPedido",
		data : {
			id : id
		},
		type : 'POST',
		success:function(data){
			$('#result').html(data);
			var i = 0;
			var dados = "<table class='tabretorno'>"
					+ "<tr>" 
					//		"<th>Codigo de barras</th>" 
					+ "<th>Nome do Produto</th>"
					+ "<th>Valor</th>" + "<th>Quantidade</th></tr>";
				//	+ "<th>Sub-total</th>";
			while ($("#nome_prod" + i).val() != null) {
				if(i%2!=0){
					 impar="class='impar'";
					
				}else{
					 impar="";
				}
				
			dados +=  "<tr "+impar+"> " +
			//"<td><span id='idp"+ i+ "'>"+ $("#id_produto" + i).val()+ "</td>" +
			"<td><span id='nomep"+ i+ "'>"+ $("#nome_prod" + i).val()+ "</td>" +
			"<td><span id='valorp"+ i+ "'>"+ $("#valor_produto" + i).val()+ "</td>" +
			"<td><span id='quantidadep"+ i+ "'>"+ $("#qtd_prod" + i).val()+ "</td></tr>";
		//	"<td><span id='subtotalp"+ i+ "'>"+ $("#subtotal" + i).val()+ "</td>"+ "</tr>";
							
				i++;
				
	}
			
			dados += "</table>  <div class='fechar' onclick='fechadetalhe()'>X</div>";
			$('#detalhes').html(dados);
			
			$('#detalhes').show();
			
		}
		
		
		
	});
	
	
	
}


function cExcluirPedido(pk){
	$('#procli .fundoq').hide();
	$("#procli .question").hide();
}

function excluiPedido(pk){
	$.ajax({
		url:'DeletaPedido',
		data: { id: $("#id"+pk).val()},
		type:'POST',	
		success: function(back){
			
	
			cExcluirCompra(pk);
			
			mensagem("green", "Pedido Excluida");
			BuscaPedido();
			
		},
		error:function(){
			cExcluirCompra(pk);
			BuscaPedido();
	
			}
	});
}


function limpapedi(){
$("input").val('');
$("textarea").val('');
$(".tabelaForn").html('');
dadosforn='';
$(".confirmar").val('Confirmar');
}