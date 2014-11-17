/*tela Novo Produto*/
function novoProdutofaz() {
	$.ajax({
		url : 'NovoProduto',
		data : $('#formulario').serialize(),
		type : 'POST',
		success : function() {
			$('#cadastro').fadeIn();
			mensagem("green", "O Produo foi cadastrado!!!!");
			$('.cad input').val('');
		},
		error : function() {
			$('#cadastro').fadeIn();
			mensagem("red", "Ocorreu um erro, o Produto não foi adicionado!!");
		}
	});
}

/* tela Novo Produto */

/* produto existente */

function pExcluiProduto(pk) {
	var pergunta = "<span style='width: 589px;    font-size: 16px;    font-weight: bold;'"
			+ ">Voc&ecirc; realmente quer Excluir:<br>"
			+ $('#nome' + pk).val()
			+ "?</span>"
			+ " <div class='yes' onclick='excluiProduto("
			+ pk
			+ ")'>Sim</div> <div class='nope' onclick='cExcluirProduto("
			+ pk
			+ ")'>N&atilde;o</div>";
	$('#procli .fundoq').show();
	$("#procli .question").show();
	$("#procli .question").html(pergunta);

}

function cExcluirProduto(pk) {
	$('#procli .fundoq').hide();
	$("#procli .question").hide();
}

function excluiProduto(pk) {
	$.ajax({
		url : 'ExcluiProduto',
		data : {
			id : $("#id" + pk).val()
		},
		type : 'POST',
		success : function(back) {

			cEditaProduto(pk);
			mensagem("green", "Produto Excluido");
			buscaDadosProduto();

		},
		error : function() {
			cEditaProduto(pk);
			buscaDadosProduto();

		}
	});
}
/* Fim da funções do NovoCliente.jsp */
/* Começa o cliente exeistente */

function pEditaProduto(pk) {
	var pergunta = "<span style='width: 589px;    font-size: 16px;    font-weight: bold;'"
			+ ">Voc&ecirc; realmente quer editar:<br>"
			+ $('#nome' + pk).val()
			+ "?</span>"
			+ " <div class='yes' onclick='editaProduto("
			+ pk
			+ ")'>Sim</div> <div class='nope' onclick='cEditaProduto("
			+ pk
			+ ")'>N&atilde;o</div>";
	$('#procli .fundoq').show();
	$("#procli .question").show();
	$("#procli .question").html(pergunta);
}

function editaProduto(pk) {
	$.ajax({
		url : 'EditaProduto',
		data : {
			id : $(".id" + pk).val(),
			nome : $(".Nome" + pk).val(),
			valor : $(".valor" + pk).val(),
			fabricante : $(".fabricante" + pk).val(),
			qtd : $(".quantidade" + pk).val(),
			estoque : $(".estoque" + pk).val(),
			validade : $(".validade" + pk).val()
		},
		type : 'POST',

		success : function(back) {
			cEditaProduto(pk);
			buscaDadosProduto();
			mensagem('green', 'O produto alterado com sucesso');
		},
		error : function() {
			cEditaProduto(pk);
			buscaDadosProduto();
			mensagem('red', 'O produto nao foi alterado - ERRO');
		}
	});

}
function cEditaProduto(pk) {

	$(".id" + pk).hide();
	$(".Nome" + pk).hide();
	$(".valor" + pk).hide();
	$(".fabricante" + pk).hide();
	$(".quantidade" + pk).hide();
	$(".estoque" + pk).hide();
	$(".validade" + pk).hide();

	$("#tabdados #id" + pk).show();
	$("#tabdados #Nome" + pk).show();
	$("#tabdados #valor" + pk).show();
	$("#tabdados #fabricante" + pk).show();
	$("#tabdados #quantidade" + pk).show();
	$("#tabdados #estoque" + pk).show();
	$("#tabdados #validade" + pk).show();
	$("#tabdados #edita" + pk).show();
	$("#tabdados #salva" + pk).hide();

	$('#procli .fundoq').hide();
	$("#procli .question").hide();

}

function mostraeditaProduto(pk) {

	$(".id" + pk).show();
	$(".Nome" + pk).show();
	$(".valor" + pk).show();
	$(".fabricante" + pk).show();
	$(".quantidade" + pk).show();
	$(".estoque" + pk).show();
	$(".validade" + pk).show();

	$("#tabdados #id" + pk).hide();
	$("#tabdados #Nome" + pk).hide();
	$("#tabdados #valor" + pk).hide();
	$("#tabdados #fabricante" + pk).hide();
	$("#tabdados #quantidade" + pk).hide();
	$("#tabdados #estoque" + pk).hide();
	$("#tabdados #validade" + pk).hide();
	$("#tabdados #edita" + pk).hide();
	$("#tabdados #salva" + pk).show();
}

function buscaDadosProduto() {

	$
			.ajax({
				url : "BuscaProduto",
				data : {
					nome : $("#nome").val(),
					id : $("#codigo").val(),
					valorMin : $("#valorMin").val(),
					valorMax : $("#valorMin").val(),
					fabricante : $("#fabricante").val(),
					validade : $("#validade").val()
				},
				type : 'POST',
				success : function(data) {
					$('#result').html(data);
					var i = 0;
					var dados = "<div id='collapse4' class='body'><table id='dataTable' class='table table-bordered table-condensed table-hover table-striped' >"
							+ "<thead><tr><th>id</th>"
							+ "<th>Nome</th>"
							+ "<th>valor</th>"
							+ "<th>fabricante</th>"
							+ "<th>quantidade</th>"
							+ "<th>estoque</th>"
							+ "<th>validade</th>"
							+ "<th>Editar</th>"
							+ "<th>Excluir</th></tr></thead><tbody>";
					while ($("#nome" + i).val() != null) {
						dados += "" + "<tr> " + "<td><span id='id"
								+ i
								+ "'>"
								+ $("#id" + i).val()
								+ "</span><input style='width:100%'  type='text' class='id"
								+ i
								+ "' value='"
								+ $("#id" + i).val()
								+ "'></input></td>"
								+ "<td><span id='Nome"
								+ i
								+ "'>"
								+ $("#nome" + i).val()
								+ " </span><input style='width:100%'  type='text' class='Nome"
								+ i
								+ "' value='"
								+ $("#nome" + i).val()
								+ "'></input></td>"
								+ "<td><span id='valor"
								+ i
								+ "'>"
								+ $("#valor" + i).val()
								+ "</span><input style='width:100%'  type='text' class='valor"
								+ i
								+ "' value='"
								+ $("#valor" + i).val()
								+ "'></input></td>"
								+ "<td><span id='fabricante"
								+ i
								+ "'>"
								+ $("#fabricante" + i).val()
								+ "</span><input style='width:100%'  type='text' class='fabricante"
								+ i
								+ "' value='"
								+ $("#fabricante" + i).val()
								+ "'></input></td>"
								+ "<td><span id='quantidade"
								+ i
								+ "'>"
								+ $("#quantidade" + i).val()
								+ "</span><input style='width:100%'  type='text' class='quantidade"
								+ i
								+ "' value='"
								+ $("#quantidade" + i).val()
								+ "'></input></td>"
								+ "<td><span id='estoque"
								+ i
								+ "'>"
								+ $("#estoque" + i).val()
								+ "</span><input style='width:100%'  type='text' class='estoque"
								+ i
								+ "' value='"
								+ $("#estoque" + i).val()
								+ "'></input></td>"
								+ "<td><span id='validade"
								+ i
								+ "'>"
								+ $("#validade" + i).val()
								+ "</span><input style='width:100%'  type='text' class='validade"
								+ i
								+ "' value='"
								+ $("#validade" + i).val()
								+ "'></input></td>"
								+ "<td><span  class='clickable glyphicon glyphicon-pencil' id='edita"
								+ i
								+ "' onclick='mostraeditaProduto("
								+ i
								+ ")' ></span><span  class='clickable glyphicon glyphicon-save'  id='salva"
								+ i
								+ "' onclick='pEditaProduto("
								+ i
								+ ")' style='display:none'></span><span class='clickable glyphicon glyphicon-floppy-remove'  id='salva"
								+ i
								+ "' onclick='cEditaProduto("
								+ i
								+ ")' style='display:none' ></span></td>"
								+ "<td><span class='clickable glyphicon glyphicon-remove' onclick='pExcluiProduto("
								+ i + ")' ></span></td>" + "</tr>";
						i++;

					}
					dados += " </tbody></table></div> " + " <script>"
							+ " $(d()); function d() {"
							+ "   Metis.MetisTable();"
							+ "   Metis.metisSortable();" + "  }"
							+ " </script>";

					$('#tabdados').html(dados);
				}
			});
}

function carregaProduto(i) {
	$("#nome").val($("#nome" + i).val());
	if ($("#doc" + i).val() > 9) {
		$("#doc").val("cnpj");
	} else {
		$("#doc").val("cpf");
	}
	$("#docNumero").val($("#doc" + i).val());
	$("#telefone").val($("#telefone" + i).val());
	$("#email").val($("#email" + i).val());
	$("#dataNascimento").val($("#data" + i).val());
}
