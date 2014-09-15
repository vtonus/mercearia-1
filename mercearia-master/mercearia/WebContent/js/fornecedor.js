/*funções do NovoCliente.jsp*/
function novoFornecedor(){
	$.ajax({
		url:'NovoFornecedor',
		data:$('#formulario').serialize(),
		type:'POST',		
		sucess:function(){
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
				 " <div class='yes' onclick='excluiCliente("+pk+")'>Sim</div> <div class='nope' onclick='cExcluirCliente("+pk+")'>N&atilde;o</div>";
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
		url:'ExcluiCliente',
		data: { id: $("#id"+pk).val()},
		type:'POST',	
		success: function(back){
			
	
			cEditaFornecedor(pk);
			$("#procli .resposta").fadeIn();
			setTimeout(function(){$("#procli .resposta").fadeOut();},3000);
			console.log('teste2');
			$("#procli .resposta").html("Cliente Excluido com sucesso!!");
			setTimeout(function(){
			$('.content').load('ProcuraCliente.jsp');
			console.log('teste3');
			buscaDadosFornecedor();},3000);
			
		},
		error:function(){
			cEditaFornecedor(pk);
			buscaDadosFornecedor();
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
		id:$("#id"+pk).val(),nome:$(".nome"+pk).val(),cpf:$(".doc"+pk).val(),telefone:$(".telefone"+pk).val(),email:$(".email"+pk).val(),sexo:$(".sexo1"+pk).val(),dataDeNascimento:$(".data"+pk).val(),
		},
		type: 'POST',
		success: function(back){
			cEditaCFornecedor(pk);
			buscaDadosFornecedor();
			$("#procli .resposta").fadeIn();
			setTimeout(function(){$("#procli .resposta").fadeOut();},3000);
			$("#procli .resposta").html("	Cliente Editado com sucesso!!");
		},
		error:function(){
			cEditaFornecedor(pk);
			buscaDadosFornecedor();
			$("#procli .resposta").fadeIn();
			setTimeout(function(){$("#procli .resposta").fadeOut();},3000);
			$("#procli .resposta").css({
				color:'red',
				borderColor:"red"
			});
			
			$("#procli .resposta").html("Ocorreu um erro, o Cliente não foi editado!!");
		}
	});
	
}

function cEditaFornecedor(pk){
	$(".nome"+pk).hide();
	$(".telefone"+pk).hide();
	$(".doc"+pk).hide();
	$(".email"+pk).hide();
	$(".sexo"+pk).hide();
	$(".data"+pk).hide();
	
	$(".tabretorno #nome"+pk).show();
	$(".tabretorno #telefone"+pk).show();
	$(".tabretorno #doc"+pk).show();
	$(".tabretorno #email"+pk).show();
	$(".tabretorno #sexo"+pk).show();
	$(".tabretorno #data"+pk).show();
	$(".tabretorno #edita"+pk).show();
	$(".tabretorno #salva"+pk).hide();
	
	$('#procli .fundoq').hide();
	$("#procli .question").hide();
	
}

function mostraeditaFornecedor(pk){

	$(".nome"+pk).show();
	$(".telefone"+pk).show();
	$(".doc"+pk).show();
	$(".email"+pk).show();
	$(".sexo"+pk).show();
	$(".data"+pk).show();
	
	$(".tabretorno #nome"+pk).hide();
	$(".tabretorno #telefone"+pk).hide();
	$(".tabretorno #doc"+pk).hide();
	$(".tabretorno #email"+pk).hide();
	$(".tabretorno #sexo"+pk).hide();
	$(".tabretorno #data"+pk).hide();
	$(".tabretorno #edita"+pk).hide();
	$(".tabretorno #salva"+pk).show();
}




function buscaDadosFornecedor() {
	
	$.ajax({
		url:"BuscaFornecedor",
		data:{palavraChave : $("#palavraChave").val(),
		parametro : $("#parametro").val()},
		type: 'POST',
		success: function(data){
			$('#result').html(data);
			var i=0;
		var	dados="<table class='tabretorno'>" +
					"<tr><th>Nome</th>" +
					"<th>Telefone</th>" +
					"<th>CPF</th>" +
					"<th>Email</th>" +
					"<th>sexo</th>" +
					"<th>data</th>" +
					"<th>Editar</th>" +
					"<th>Excluir</th></tr>"
				;
			while($("#nome"+i).val()!=null){
			  dados+="" +
						"<tr> " +
				"<td><span id='nome"+i+"'>" + $("#nome"+i).val()+ "</span><input  type='text' class='nome"+i+"' value='"+$("#nome"+i).val()+"'></input></td>" +
		 		"<td><span id='telefone"+i+"'>" + $("#telefone"+i).val()+ " </span><input  type='text' class='telefone"+i+"' value='"+$("#telefone"+i).val()+"'></input></td>" +
		 		"<td><span id='doc"+i+"'>" + $("#cpf"+i).val()+ "</span><input  type='text' class='doc"+i+"' value='"+$("#cpf"+i).val()+"'></input></td>" +
		 		"<td><span id='email"+i+"'>" + $("#email"+i).val()+ "</span><input  type='text' class='email"+i+"' value='"+$("#email"+i).val()+"'></input></td>" +
		 		"<td><span id='sexo"+i+"' >" + $("#sexo"+i).val()+ "</span><label class='estiloso sexo"+i+"' ><select  name='sexo' class='sexo"+i+" sexo1"+i+"' value='"+$("#sexo"+i).val()+"'><option value='m'>Masculino</option><option value='f'>Feminino </option></select></label> </td>" +
		 		"<td><span id='data"+i+"'>" + $("#dataNascimento"+i).val()+ "</span><input  type='text' class='data"+i+"' value='"+$("#dataNascimento"+i).val()+"'></input></td>" +
		 		"<td><img id='edita"+i+"' onclick='mostraedita("+i+")' src='../images/edita.png' /><img id='salva"+i+"' onclick='pEditaFornecedor("+i+")' style='display:none' src='../images/salva.png' /><img id='salva"+i+"' onclick='cEditaFornecedor("+i+")' style='display:none' src='../images/close15.png' /></td>"+
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


