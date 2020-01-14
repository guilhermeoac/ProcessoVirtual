import {Component, OnInit, ViewChild} from '@angular/core';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {Router} from '@angular/router';
import {MatriculaService} from './matricula.service';
import {Table} from 'primeng';
import {finalize} from 'rxjs/operators';
import {Page} from '../util/page';
import {AcaoEnum} from '../enumerations/acao.enum';
import {MatriculaList} from './matricula-list.model';
import {MatriculaFilter} from './matricula-filter.model';


@Component({
  selector: 'app-matricula',
  templateUrl: './matricula.component.html',
  styleUrls: []
})
export class MatriculaComponent implements OnInit {

  @ViewChild('dt', {static: false}) datatable: Table;

  matriculas: Page<MatriculaList> = new Page;
  matricula: MatriculaFilter = new MatriculaFilter();


  linhaSelecionada: any;
  acoes = AcaoEnum;

  cols = [
    { field: 'numeroAlunos', header: 'Numero de Aluno', width: '165px', text: true},
    { field: 'nomeDisciplina', header: 'Disciplina' , width: '185px', text: true},
  ];

  @BlockUI() blockUI: NgBlockUI;

  constructor(private router: Router,
              private service: MatriculaService
  ) {}

  ngOnInit() {
    this.getAllDeclaracao();
  }

  buttonClickView() {
    this.router.navigate(['matricula/visualizar', this.linhaSelecionada.id]);
  }

  buttonClickEdit() {
    this.router.navigate(['matricula/editar', this.linhaSelecionada.id]);
  }

  getAllDeclaracao() {
    this.blockUI.start('Carregando...');
    this.service.getDeclaracaoFilter(this.matricula, this.datatable).pipe(finalize(() => this.blockUI.stop()))
      .subscribe( response => this.matriculas = response );

  }

  getAllDeclaracaoPage() {
    this.matricula = new MatriculaFilter();
    this.getAllDeclaracao();
  }

  getAllMatriculaPesquisar() {
    this.datatable.reset();
    if ((this.matricula.dataInicio != null || this.matricula.dataFim != null) && this.matricula.dataInicio.getTime() - this.matricula.dataFim.getTime() > 0) {
      return;
    }

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
