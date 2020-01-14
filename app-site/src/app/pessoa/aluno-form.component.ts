import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Aluno} from './aluno.model';
import {AcaoEnum} from '../enumerations/acao.enum';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {AlunoService} from './aluno.service';
import {ConfirmationService, MessageService} from 'primeng';
import {finalize} from 'rxjs/operators';
import {SexoEnum} from "../enumerations/sexos.enum";


@Component({
  selector: 'app-aluno-form',
  templateUrl: './aluno-form.component.html',
  styleUrls: []
})
export class AlunoFormComponent implements OnInit {

  acao: string;
  aluno: Aluno = new Aluno();
  acoes = AcaoEnum;
  sexos = SexoEnum.sexosSelectItem;

  @BlockUI() blockUI: NgBlockUI;

  constructor(
    private alunoService: AlunoService,
    private route: ActivatedRoute,
    private router: Router,
    private messageService: MessageService
  ) { }

  ngOnInit() {
    this.verificarParametros();
  }

  verificarParametros() {
    this.route.params.subscribe((params) => {
      if (params['id']) {
        this.load(params['id']);
      }
      this.acao = params['acao'];
    });
  }

  load(id){
    this.blockUI.start('Carregando...');
    this.alunoService.findById(id).pipe(finalize(() => this.blockUI.stop()))
      .subscribe(aluno => {
        this.aluno = aluno;
      });
  }

  persistirRegistro(form: NgForm){
    if (!form.valid){
      this.messageService.add({detail: 'Há campos obrigatórios que não foram informados!'});
      return;
    }
        this.blockUI.start('Salvando...');
        this.alunoService.save(this.aluno).pipe(finalize(() => this.blockUI.stop()))
          .subscribe( () => {
            this.router.navigate( [ '/aluno' ] );
            this.messageService.add(this.acao == this.acoes.INSERIR ? {detail: 'Aluno incluido com sucesso'} : {detail: 'Aluno alterado com sucesso'});
          });
  }
}
