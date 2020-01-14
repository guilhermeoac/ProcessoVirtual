import {Component, OnInit, ViewChild} from '@angular/core';
import {Aluno} from './aluno.model';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {Router} from '@angular/router';
import {AlunoService} from './aluno.service';
import {ConfirmationService, Table} from 'primeng';
import {finalize} from 'rxjs/operators';
import {Page} from '../util/page';
import {AcaoEnum} from '../enumerations/acao.enum';
import {SexoEnum} from "../enumerations/sexos.enum";


@Component({
  selector: 'app-aluno',
  templateUrl: './aluno.component.html',
  styleUrls: []
})
export class AlunoComponent implements OnInit {

  @ViewChild('dt', {static: false}) datatable: Table;

  alunos: Page<Aluno> = new Page;
  aluno: Aluno = new Aluno();


  linhaSelecionada: any;
  acoes = AcaoEnum;
  sexos = SexoEnum.sexosSelectItem;

  cols = [
    { field: 'nome', header: 'Nome', width: '165px', text: true},
    { field: 'sexo', header: 'Sexo' , width: '185px', text: true, options: this.sexos},
    { field: 'email', header: 'E-mail', width: '185px', text: true},
    { field: 'telefone', header: 'Telefone', width: '185px', text: true},
    { field: 'cpf', header: 'CPF', width: '185px', text: true},
  ];

  @BlockUI() blockUI: NgBlockUI;

  constructor(private router: Router,
              private service: AlunoService,
              private confirmationService: ConfirmationService,
  ) {}

  ngOnInit() {
    this.getAllDeclaracao();
  }

  buttonClickView() {
    this.router.navigate(['aluno/visualizar', this.linhaSelecionada.id]);
  }

  buttonClickEdit() {
    this.router.navigate(['aluno/editar', this.linhaSelecionada.id]);
  }

  getAllDeclaracao() {
    this.blockUI.start('Carregando...');
    this.service.getDeclaracaoFilter(this.aluno, this.datatable).pipe(finalize(() => this.blockUI.stop()))
      .subscribe( response => this.alunos = response );

  }

  getAllDeclaracaoPage() {
    this.aluno = new Aluno();
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
