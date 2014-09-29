/*funções do NovoCliente.jsp*/
function novoFornecedor(){
	$.ajax({
		url:'NovoFornecedor',
		data:$('#formulario').serialize(),
		type:'POST',		
		success:function(){
			$('#cadastro').fadeIn();		
			mensagem("green","O Fornecedor foi cadastrado!!");
		},
		error:function(){
			$('#cadastro').fadeIn();		
			mensagem("red","Ocorreu um erro, o Fornecedor não foi adicionado!!");
		}
	});
	
}
function pExcluiFornecedor(pk){
	var pergunta=	"<span style='width: 589px;    font-size: 16px;    font-weight: bold;'"+
	">Voc&ecirc; realmente quer Excluir:<br>" + $('#nome'+pk).val()+"?</span>"+
				 " <div class='yes' onclick='excluiFornecedor("+pk+")'>Sim</div> <div class='nope' onclick='cExcluirFornecedor("+pk+")'>N&atilde;o</div>";
		$('#procli .fundoq').show();
		$("#procli .question").show();
		$("#procli .question").html(pergunta);

}
	
function cExcluirFornecedor(pk){
	$('#procli .fundoq').hide();
	$("#procli .question").hide();
}

function excluiFornecedor(pk){
	$.ajax({
		url:'ExcluiFornecedor',
		data: { id: $("#id"+pk).val()},
		type:'POST',	
		success: function(back){
			
	
			cEditaFornecedor(pk);
			mensagem("green", "FOrnecedor Excluido");
			buscaDadosFornecedor();
			//buscaDadosCliente();
			
		},
		error:function(){
			cEditaFornecedor(pk);
			buscaDadosFornecedor();
			//buscaDadosCliente();
			/*$("#procli .resposta").fadeIn();
			setTimeout(function(){$("#procli .resposta").fadeOut();},3000);
			$("#procli .resposta").css({
				color:'red',
				borderColor:"red",
					
				});
			$("#procli .resposta").html("Ocorreu um erro, o Cliente não foi excluido!!");*/
	
			}
	});

}
/*Fim da funções do NovoCliente.jsp*/
/* Começa o cliente exeistente*/

function pEditaFornecedor(pk){
var pergunta=	"<span style='width: 589px;    font-size: 16px;    font-weight: bold;'"+
">Voc&ecirc; realmente quer editar:<br>" + $('#nome'+pk).val()+"?</span>"+
			 " <div class='yes' onclick='editaFornecedor("+pk+")'>Sim</div> <div class='nope' onclick='cEditaFornecedor("+pk+")'>N&atilde;o</div>";
	$('#procli .fundoq').show();
	$("#procli .question").show();
	$("#procli .question").html(pergunta);
}
	
function editaFornecedor(pk){
	
	
	$.ajax({
		url:'EditaFornecedor',
		data:{
		id:$("#id"+pk).val(),nome:$(".nome"+pk).val(),cnpj:$(".cnpj"+pk).val()
		,telefone:$(".telefone"+pk).val(),email:$(".email"+pk).val(),
		endereco:$(".endereco"+pk).val()
		},
		type: 'POST',
		success: function(back){
			cEditaFornecedor(pk);
			buscaDadosFornecedor();
			mensagem("green", "Fornecedor editado.");
		},
		error:function(){
			cEditaFornecedor(pk);
			buscaDadosFornecedor();
			mensagem("red", "Fornecedor não foi editado.");
		}
	});
	
}

function cEditaFornecedor(pk){

	$(".id"+pk).hide();
	$(".nome"+pk).hide();
	$(".cnpj"+pk).hide();
	$(".telefone"+pk).hide();
	$(".email"+pk).hide();
	$(".endereco"+pk).hide();
	
	$(".tabretorno #id"+pk).show();
	$(".tabretorno #nome"+pk).show();
	$(".tabretorno #cnpj"+pk).show();
	$(".tabretorno #telefone"+pk).show();
	$(".tabretorno #email"+pk).show();
	$(".tabretorno #endereco"+pk).show();
	$(".tabretorno #edita"+pk).show();
	$(".tabretorno #salva"+pk).hide();
	
	$('#procli .fundoq').hide();
	$("#procli .question").hide();
	
}

function mostraeditaFornecedor(pk){

	
	
	$(".id"+pk).show();
	$(".nome"+pk).show();
	$(".cnpj"+pk).show();
	$(".telefone"+pk).show();
	$(".email"+pk).show();
	$(".endereco"+pk).show();
	
	$(".tabretorno #id"+pk).hide();
	$(".tabretorno #nome"+pk).hide();
	$(".tabretorno #cnpj"+pk).hide();
	$(".tabretorno #telefone"+pk).hide();
	$(".tabretorno #email"+pk).hide();
	$(".tabretorno #endereco"+pk).hide();
	$(".tabretorno #edita"+pk).hide();
	$(".tabretorno #salva"+pk).show();
}




function buscaDadosFornecedor() {
	$('#tabdados').html('');
	$('#result').html('');
	$.ajax({
		url:"BuscaFornecedor",
		data:{palavraChave : $("#palavraChave").val(),
		parametro : $("#parametro").val()},
		type: 'POST',
		success: function(data){
			$('#result').html(data);
			var i=0;
		var	dados="<table class='tabretorno'>" +
					"<tr><th>Id</th>" +
					"<th>Nome</th>" +
					"<th>CNPJ</th>" +
					"<th>Telefone</th>" +
					"<th>Email</th>" +
					"<th>Endereco</th>" +
					"<th>Editar</th>" +
					"<th>Excluir</th></tr>";
				
			while($("#nome"+i).val()!=null){
			  dados+="" +
						"<tr> " +
				"<td><span id='id"+i+"'>" + $("#id"+i).val()+ "</span><input  type='text' class='id"+i+"' value='"+$("#id"+i).val()+"'></input></td>" +
		 		"<td><span id='nome"+i+"'>" + $("#nome"+i).val()+ " </span><input  type='text' class='nome"+i+"' value='"+$("#nome"+i).val()+"'></input></td>" +
		 		"<td><span id='cnpj"+i+"'>" + $("#cnpj"+i).val()+ "</span><input  type='text' class='cnpj"+i+"' value='"+$("#cnpj"+i).val()+"'></input></td>" +
		 		"<td><span id='telefone"+i+"'>" + $("#telefone"+i).val()+ "</span><input  type='text' class='telefone"+i+"' value='"+$("#telefone"+i).val()+"'></input></td>" +
		 		"<td><span id='email"+i+"'>" + $("#email"+i).val()+ "</span><input  type='text' class='email"+i+"' value='"+$("#email"+i).val()+"'></input></td>" +
		 		"<td><span id='endereco"+i+"'>" + $("#endereco"+i).val()+ "</span><input  type='text' class='endereco"+i+"' value='"+$("#endereco"+i).val()+"'></input></td>" +
		 		
		 		"<td><img id='edita"+i+"' onclick='mostraeditaFornecedor("+i+")' src='../images/edita.png' /><img id='salva"+i+"' onclick='pEditaFornecedor("+i+")' style='display:none' src='../images/salva.png' /><img id='salva"+i+"' onclick='cEditaFornecedor("+i+")' style='display:none' src='../images/close15.png' /></td>"+
		 		"<td><img onclick='pExcluiFornecedor("+i+")' src='../images/exclui.png' /></td>"+"</tr>";
		 		 i++;
				
		}		
			dados+="</table>";
			
		
			$('#tabdados').html(dados);
		}
	});
}



function carregaFornecedor(i){
	$("#nome").val($("#nome"+i).val());
	if ($("#doc"+i).val() > 9){
	$("#doc").val("cnpj");
	}
	else {
	$("#doc").val("cpf");
	}
	$("#docNumero").val($("#doc"+i).val());
	$("#telefone").val($("#telefone"+i).val());
	$("#email").val($("#email"+i).val());
	$("#dataNascimento").val($("#data"+i).val());
}


