var dados;
var contrforn=0;
function remeped(id){
	$('#tr'+id).remove();
	console.log(id);
	dados=$('.tabelaForn').html();
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

	dados += "<tr id='tr"+contrforn+"'" + impar + " ondblclick=removeproduct(" + contrforn + ")><td> "
			+ "<input readonly id='cod"+contrforn+"' type='text' value='"+$('#cod').val()+"'/></td><td> <input readonly id='prod"+contrforn+"' type='text' value='"+$('#prod').val()+"'/></td>"
			+ "<td> <input readonly id='vlrud"+contrforn+"' type='text' value='"+$('#unid').val()+"'/></td>" + "<td><input readonly id='qtd"+contrforn+"' type='text' value='"+$('#qtd').val()+"'/> </td>" + "<td><input readonly id='cod"+contrforn+"' type='text' value='"+$('#vlr').val()+"'/></td><td onclick='remeped("+contrforn+")'>X</td></tr>";
	$('.tabelaForn').html(dados);
	contrforn++;
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
			produto : $("#cod").val()
		},
		success : function(back) {
			if (back) {
				$('.retornando').html(back);
				//
				$("#prod").val($('#nomeAC').val());
				$("#unid").val($('#valorAC').val());
				$('#qtd').focus();
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
	var total=$('#unid').val()*$('#qtd').val();
	var valortotalcompra = total.toFixed(2);
	valortotalcompra = valortotalcompra.replace(".",",");
	$('#vlr').val(valortotalcompra);
	
	 if(teclado=='13'){
	adcProdutoForn();
	}
	
	
}


function BuscaPedido(){
	
	$.ajax({
		url:"BuscaPedido",
		data:{palavraChave : $("#palavraChave").val(),
		parametro : $("#parametro").val()},
		type: 'POST',
		success: function(data){
			
		
		}
	
	
	});
}