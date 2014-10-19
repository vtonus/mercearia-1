
<div id="NovoCliente">
<span class="titulo">CADASTRO DE NOVO FORNECEDOR</span>
<form name="myForm" id="formulario" onSubmit="JavaScript:handleClick6()">
<table class="cad">

		
		<tr>	
			<td>	<label for="nome">Nome*:</label></td>
			<td>	<input required type="text" name="nome" id="nome" autofocus> </td>
		</tr>
			<tr>
			<td>	<label for="doc">CNPJ:</label></td>
			<td>	<input type="text" pattern="[0-9]{9}[A-Za-z0-9]{2}" id="cpf" name="CNPJ" > </td>
 			</tr>
 			<tr>
 			<td>	<label for="tel">Telefone:</label></td>
 			<td>	<input id="Telefone" type="text" name="telefone" min="8" max="14"></td>
			</tr>
			<tr>
 			<td>	<label for="end">Endereço:</label><br></td>
 			<td>	<input type="text" name="endereco" id="end"> </td>
 			</tr>
 			<tr>
 			<td>	<label for="email">Email:</label></td>
			<td>	<input id="email" type="email" name="email"></td>
			</tr>
		
			
					</table>
				
					<input type="submit" class="confirmar" value="confirmar"></input>
	 				<div class="limpar" onclick="$('.cad input').val('');">Limpar</input>
 				</form>
	</div>
