package ingresso;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.helper.GerenciadorDeSessao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessaoTest {

	@Test
	public void garanteQueNaoDevePermitirSessaoNoMesmoHorario() {

		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI");
		LocalTime horario = LocalTime.parse("10:00:00");

		Sala sala = new Sala("");
		List<Sessao> sessoes = Arrays.asList(new Sessao(horario, sala, filme));

		Sessao sessao = new Sessao(horario, sala, filme);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciador.cabe(sessao));
	}

	@Test
	public void garanteQueNaoDevePermitirSessoesTerminandoDentroDoHorarioDeUmaSessaoExistente() {

		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI");
		LocalTime horario = LocalTime.parse("10:00:00");

		Sala sala = new Sala("");
		List<Sessao> sessoes = Arrays.asList(new Sessao(horario, sala, filme));

		Sessao sessao = new Sessao(horario.minusHours(1), sala, filme);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciador.cabe(sessao));
	}

	@Test
	public void garanteQueNaoDevePermitirSessoesDentroDentroDoHorarioDeUmaSessaoExistente() {

		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI");
		LocalTime horario = LocalTime.parse("10:00:00");

		Sala sala = new Sala("");
		List<Sessao> sessoes = Arrays.asList(new Sessao(horario, sala, filme));

		Sessao sessao = new Sessao(horario.plusHours(1), sala, filme);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciador.cabe(sessao));
	}

	@Test
	public void garanteQueDevePermitirUmaInsercaoEntreDoisFilmes() {

		Sala sala = new Sala("");

		Filme filme1 = new Filme("Rogue One", Duration.ofMinutes(90), "SCI-FI");
		LocalTime dezHoras = LocalTime.parse("10:00:00");
		Sessao sessaoDasDez = new Sessao(dezHoras, sala, filme1);

		Filme filme2 = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI");
		LocalTime dezoitoHoras = LocalTime.parse("18:00:00");
		Sessao sessaoDasDezoito = new Sessao(dezoitoHoras, sala, filme2);

		List<Sessao> sessoes = Arrays.asList(sessaoDasDez, sessaoDasDezoito);

		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciador.cabe(new Sessao(LocalTime.parse("13:00:00"), sala, filme2)));
	}
}
