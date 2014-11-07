/*funções do NovoCliente.jsp*/
function novoFuncionario(){
	$.ajax({
		url:'NovoFuncionario',
		data:$('#formulario').serialize(),
		type:'POST',		
		sucess:function(){
			$('#cadastro').fadeIn();		
			mensagem("green","O Funcionario foi cadastrado!!");
		},
		error:function(){
			$('#cadastro').fadeIn();		
			mensagem("red","Ocorreu um erro, o Funcionario não foi adicionado!!");
		}
	});
	
}
function pExcluiFuncionario(pk){
	var pergunta=	"<span style='width: 589px;    font-size: 16px;    font-weight: bold;'"+
	">Voc&ecirc; realmente quer Excluir:<br>" + $('#nome'+pk).val()+"?</span>"+
				 " <div class='yes' onclick='excluiFuncionario("+pk+")'>Sim</div> <div class='nope' onclick='cExcluirFuncionario("+pk+")'>N&atilde;o</div>";
		$('#procli .fundoq').show();
		$("#procli .question").show();
		$("#procli .question").html(pergunta);

}
	
function cExcluirFuncionario(pk){
	$('#procli .fundoq').hide();
	$("#procli .question").hide();
}

function excluiFuncionario(pk){
	$.ajax({
		url:'ExcluiFuncionario',
		data: { id: $("#id"+pk).val()},
		type:'POST',	
		success: function(back){
			
	
			cEditaFuncionario(pk);
			$("#procli .resposta").fadeIn();
			setTimeout(function(){$("#procli .resposta").fadeOut();},3000);
			console.log('teste2');
			$("#procli .resposta").html("Cliente Excluido com sucesso!!");
			setTimeout(function(){
			$('.content').load('ProcuraCliente.jsp');
			console.log('teste3');
			buscaDadosFuncionario();},3000);
			
		},
		error:function(){
			cEditaFuncionario(pk);
			buscaDadosFuncionario();
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

function pEditaFuncionario(pk){
var pergunta=	"<span style='width: 589px;    font-size: 16px;    font-weight: bold;'"+
">Voc&ecirc; realmente quer editar:<br>" + $('#nome'+pk).val()+"?</span>"+
			 " <div class='yes' onclick='editaFuncionario("+pk+")'>Sim</div> <div class='nope' onclick='cEditaFuncionario("+pk+")'>N&atilde;o</div>";
	$('#procli .fundoq').show();
	$("#procli .question").show();
	$("#procli .question").html(pergunta);
}
	
function editaFuncionario(pk){
	
	
	$.ajax({
		url:'EditaFuncionario',
		data:{
		id:$("#id"+pk).val(),nome:$(".nome"+pk).val(),cpf:$(".doc"+pk).val(),telefone:$(".telefone"+pk).val(),email:$(".email"+pk).val(),dataDeNascimento:$(".data"+pk).val(),
		},
		type: 'POST',
		success: function(back){
			cEditaFuncionario(pk);
			buscaDadosFuncionario();
			$("#procli .resposta").fadeIn();
			setTimeout(function(){$("#procli .resposta").fadeOut();},3000);
			$("#procli .resposta").html("	Cliente Editado com sucesso!!");
		},
		error:function(){
			cEditaFuncionario(pk);
			buscaDadosFuncionario();
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

function cEditaFuncionario(pk){
	$(".nome"+pk).hide();
	$(".telefone"+pk).hide();
	$(".doc"+pk).hide();
	$(".email"+pk).hide();

	$(".data"+pk).hide();
	
	$(".tabretorno #nome"+pk).show();
	$(".tabretorno #telefone"+pk).show();
	$(".tabretorno #doc"+pk).show();
	$(".tabretorno #email"+pk).show();

	$(".tabretorno #data"+pk).show();
	$(".tabretorno #edita"+pk).show();
	$(".tabretorno #salva"+pk).hide();
	
	$('#procli .fundoq').hide();
	$("#procli .question").hide();
	
}

function mostraeditaFuncionario(pk){

	$(".nome"+pk).show();
	$(".telefone"+pk).show();
	$(".doc"+pk).show();
	$(".email"+pk).show();

	$(".data"+pk).show();
	
	$(".tabretorno #nome"+pk).hide();
	$(".tabretorno #telefone"+pk).hide();
	$(".tabretorno #doc"+pk).hide();
	$(".tabretorno #email"+pk).hide();

	$(".tabretorno #data"+pk).hide();
	$(".tabretorno #edita"+pk).hide();
	$(".tabretorno #salva"+pk).show();
}




function buscaDadosFuncionario() {
	
	$.ajax({
		url:"BuscaFuncionario",
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
		 	    "<td><span id='data"+i+"'>" + $("#dataNascimento"+i).val()+ "</span><input  type='date' class='data"+i+"' value='"+$("#dataNascimento"+i).val()+"'></input></td>" +
		 		"<td><img style='cursor:pointer' id='edita"+i+"' onclick='mostraeditaFuncionario("+i+")' src='../images/edita.png' /><img style='cursor:pointer;display:none' id='salva"+i+"' onclick='pEditaFuncionario("+i+")'  src='../images/salva.png' /><img id='salva"+i+"' onclick='cEditaFuncionario("+i+")' style='display:none' src='../images/close15.png' /></td>"+
		 		"<td><img style='cursor:pointer' onclick='pExcluiFuncionario("+i+")' src='../images/exclui.png' /></td>"+"</tr>";
		 		 i++;
				
		}		
			dados+="</table>";
			$('#tabdados').html(dados);
		}
	});
}



function carregaFuncionario(i){
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


