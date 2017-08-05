package br.com.caelum.ingresso.helper;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {

	private List<Sessao> sessoesDaSala;

	public GerenciadorDeSessao(List<Sessao> sessoesDaSala) {
		this.sessoesDaSala = sessoesDaSala;
	}

	public boolean cabe(final Sessao sessaoAtual) {
		Optional<Boolean> optionCabe = sessoesDaSala.stream()
				.map(sessaoExistente -> horarioIsValido(sessaoExistente, sessaoAtual)).reduce(Boolean::logicalAnd);
		return optionCabe.orElse(true);

	}

	private boolean horarioIsValido(Sessao sessaoExistente, Sessao sessaoAtual) {
		LocalTime horarioSessao = sessaoExistente.getHorario();
		LocalTime horarioAtual = sessaoAtual.getHorario();
		boolean ehAntes = horarioAtual.isBefore(horarioSessao);

		if (ehAntes) {
			return sessaoAtual.getHorarioTermino().isBefore(horarioSessao);
		} else {
			return sessaoExistente.getHorarioTermino().isBefore(horarioAtual);
		}
	}

}
