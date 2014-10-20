
<div id="NovoCliente">
<span class="titulo">Novo Funcionário</span>
<form name="myForm" id="formulario" onSubmit="JavaScript:handleClick8()">
<table class="cad">

		
		<tr>	
			<td>	<label for="nome">Nome*:</label></td>
			<td>	<input required type="text" name="nome" id="nome" autofocus> </td>
		</tr>
			<tr>
			<td>	<label for="doc">CPF:</label></td>
			<td>	<input type="text" pattern="[0-9]{9}[A-Za-z0-9]{2}" id="cpf" name="doc" > </td>
 			</tr>
 			
 			<tr>
 			<td>	<label for="tel">Usuario:</label></td>
 			<td>	<input id="Telefone" type="text" name="usuario" min="8" max="14"></td>
			</tr>
			
			<tr>
 			<td>	<label for="tel">senha:</label></td>
 			<td>	<input id="Telefone" type="text" name="senha" min="8" max="14"></td>
			</tr>
			
			
 			<tr>
 			<td>	<label for="tel">Telefone:</label></td>
 			<td>	<input id="Telefone" type="text" name="telefone" min="8" max="14"></td>
			</tr>
 		
 			<tr>
 			<td>	<label for="email">Email:</label></td>
			<td>	<input id="email" type="email" name="email"></td>
			</tr>
			<tr>
			<td>	<label for="dtn">Data de nascimento:</label></td>
			<td>     <input type="date" name="dtn"> <br ></td>
			</tr>	
			
					</table>
			
					<input type="submit"  value="confirmar" class="confirmar" onclick="()"></input>
	 				<div class="limpar" onclick="$('.cad input').val('');">Limpar</input>
	 					</form>

	</div>
