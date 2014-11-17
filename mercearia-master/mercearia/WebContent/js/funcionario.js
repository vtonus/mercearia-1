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
	
	$("#tabdados #nome"+pk).show();
	$("#tabdados #telefone"+pk).show();
	$("#tabdados #doc"+pk).show();
	$("#tabdados #email"+pk).show();

	$("#tabdados #data"+pk).show();
	$("#tabdados #edita"+pk).show();
	$("#tabdados #salva"+pk).hide();
	
	$('#procli .fundoq').hide();
	$("#procli .question").hide();
	
}

function mostraeditaFuncionario(pk){

	$(".nome"+pk).show();
	$(".telefone"+pk).show();
	$(".doc"+pk).show();
	$(".email"+pk).show();

	$(".data"+pk).show();
	
	$("#tabdados #nome"+pk).hide();
	$("#tabdados#telefone"+pk).hide();
	$("#tabdados #doc"+pk).hide();
	$("#tabdados #email"+pk).hide();

	$("#tabdados #data"+pk).hide();
	$("#tabdados #edita"+pk).hide();
	$("#tabdados #salva"+pk).show();
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
			var	dados="<div id='collapse4' class='body'><table id='dataTable' class='table table-bordered table-condensed table-hover table-striped' >" +
					"<thead><tr><th>Nome</th>" +
					"<th>Telefone</th>" +
					"<th>CPF</th>" +
					"<th>Email</th>" +
					"<th>data</th>" +
					"<th>Editar</th>" +
					"<th>Excluir</th></tr></thead><tbody>"
				;
			while($("#nome"+i).val()!=null){
			  dados+="" +
						"<tr> " +
				"<td><span id='nome"+i+"'>" + $("#nome"+i).val()+ "</span><input style='width:100%' type='text' class='nome"+i+"' value='"+$("#nome"+i).val()+"'></input></td>" +
		 		"<td><span id='telefone"+i+"'>" + $("#telefone"+i).val()+ " </span><input style='width:100%'  type='text' class='telefone"+i+"' value='"+$("#telefone"+i).val()+"'></input></td>" +
		 		"<td><span id='doc"+i+"'>" + $("#cpf"+i).val()+ "</span><input style='width:100%'  type='text' class='doc"+i+"' value='"+$("#cpf"+i).val()+"'></input></td>" +
		 		"<td><span id='email"+i+"'>" + $("#email"+i).val()+ "</span><input style='width:100%'  type='text' class='email"+i+"' value='"+$("#email"+i).val()+"'></input></td>" +
		 	    "<td><span id='data"+i+"'>" + $("#dataNascimento"+i).val()+ "</span><input style='width:100%'  type='date' class='data"+i+"' value='"+$("#dataNascimento"+i).val()+"'></input></td>" +
		 		"<td><span  class='clickable glyphicon glyphicon-pencil' style='cursor:pointer' id='edita"+i+"' onclick='mostraeditaFuncionario("+i+")' /><span  class='clickable glyphicon glyphicon-save'  style='cursor:pointer;display:none' id='salva"+i+"' onclick='pEditaFuncionario("+i+")'  ></span><span class='clickable glyphicon glyphicon-floppy-remove'  id='salva"+i+"' onclick='cEditaFuncionario("+i+")' style='display:none' ></span></td>"+
		 		"<td><span class='clickable glyphicon glyphicon-remove' style='cursor:pointer' onclick='pExcluiFuncionario("+i+")'/></td>"+"</tr>";
		 		 i++;
				
		}		
			dados+=" </tbody></table></div> " +
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


