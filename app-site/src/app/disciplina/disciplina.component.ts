import {Component, OnInit, ViewChild} from '@angular/core';
import {Disciplina} from './disciplina.model';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {Router} from '@angular/router';
import {DisciplinaService} from './disciplina.service';
import {ConfirmationService, Table} from 'primeng';
import {finalize} from 'rxjs/operators';
import {Page} from '../util/page';
import {AcaoEnum} from '../enumerations/acao.enum';


@Component({
  selector: 'app-disciplina',
  templateUrl: './disciplina.component.html',
  styleUrls: []
})
export class DisciplinaComponent implements OnInit {

  @ViewChild('dt', {static: false}) datatable: Table;

  disciplinas: Page<Disciplina> = new Page;
  disciplina: Disciplina = new Disciplina();


  linhaSelecionada: any;
  acoes = AcaoEnum;

  cols = [
    { field: 'nome', header: 'Nome', width: '165px', text: true},
    { field: 'limiteAlunos', header: 'Limite Alunos' , width: '185px', text: true},
    { field: 'cargaHoraria', header: 'Carga Horária', width: '185px', text: true},
  ];

  @BlockUI() blockUI: NgBlockUI;

  constructor(private router: Router,
              private service: DisciplinaService
  ) {}

  ngOnInit() {
    this.getAllDeclaracao();
  }

  buttonClickView() {
    this.router.navigate(['disciplina/visualizar', this.linhaSelecionada.id]);
  }

  buttonClickEdit() {
    this.router.navigate(['disciplina/editar', this.linhaSelecionada.id]);
  }

  getAllDeclaracao() {
    this.blockUI.start('Carregando...');
    this.service.getAll(this.disciplina, this.datatable).pipe(finalize(() => this.blockUI.stop()))
      .subscribe( response => this.disciplinas = response );

  }

  getAllDeclaracaoPage() {
    this.disciplina = new Disciplina();
    this.getAllDeclaracao();
  }

  getAllUtilizacaoPesquisar() {
    this.datatable.reset();
    this.getAllDeclaracao();
  }

  inativar() {
    this.blockUI.start('Salvando ...');
    this.service.excluir(this.linhaSelecionada.id).pipe(finalize(() => this.blockUI.stop()))
      .subscribe(() => {
        this.getAllDeclaracao();
      });
  }
}
