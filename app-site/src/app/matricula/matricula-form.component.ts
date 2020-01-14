import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Matricula} from './matricula.model';
import {AcaoEnum} from '../enumerations/acao.enum';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {MatriculaService} from './matricula.service';
import {ConfirmationService, MessageService, SelectItem} from 'primeng';
import {finalize} from 'rxjs/operators';
import {SexoEnum} from "../enumerations/sexos.enum";
import {AlunoService} from "../pessoa/aluno.service";
import {DisciplinaService} from "../disciplina/disciplina.service";


@Component({
  selector: 'app-matricula-form',
  templateUrl: './matricula-form.component.html',
  styleUrls: []
})
export class MatriculaFormComponent implements OnInit {

  acao: string;
  matricula: Matricula = new Matricula();
  alunos: SelectItem[] = [];
  disciplinas: SelectItem[] = [];
  acoes = AcaoEnum;

  @BlockUI() blockUI: NgBlockUI;

  constructor(
    private matriculaService: MatriculaService,
    private alunoService: AlunoService,
    private disciplinaService: DisciplinaService,
    private route: ActivatedRoute,
    private router: Router,
    private messageService: MessageService
  ) { }

  ngOnInit() {
    this.verificarParametros();
    this.findAlunoDropDown();
    this.findDisciplinaDropDown();
  }

  verificarParametros() {
    this.route.params.subscribe((params) => {
      if (params['id']) {
        this.load(params['id']);
      }
      this.acao = params['acao'];
    });
  }

  load(id) {
    this.blockUI.start('Carregando...');
    this.matriculaService.findById(id).pipe(finalize(() => this.blockUI.stop()))
      .subscribe(matricula => {
        this.matricula = matricula;
      });
  }

  findAlunoDropDown() {
    this.blockUI.start('Carregando...');
    this.alunoService.findDropDown().pipe(finalize(() => this.blockUI.stop()))
      .subscribe(alunos => {
        this.alunos = alunos;
      });
  }

  findDisciplinaDropDown() {
    this.blockUI.start('Carregando...');
    this.disciplinaService.findDropDown().pipe(finalize(() => this.blockUI.stop()))
      .subscribe(disciplinas => {
        this.disciplinas = disciplinas;
      });
  }

  persistirRegistro(form: NgForm){
    if (!form.valid){
      this.messageService.add({detail: 'Há campos obrigatórios que não foram informados!'});
      return;
    }
        this.blockUI.start('Salvando...');
        this.matriculaService.save(this.matricula).pipe(finalize(() => this.blockUI.stop()))
          .subscribe( () => {
            this.router.navigate( [ '/matricula' ] );
            this.messageService.add(this.acao == this.acoes.INSERIR ? {detail: 'Matricula incluido com sucesso'} : {detail: 'Matricula alterado com sucesso'});
          }, () => {this.messageService.add({detail: 'Excedeu o limite de alunos'});
           });
  }
}
