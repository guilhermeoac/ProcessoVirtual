import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AcaoEnum} from '../enumerations/acao.enum';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {DisciplinaService} from './disciplina.service';
import {MessageService} from 'primeng';
import {finalize} from 'rxjs/operators';
import {Disciplina} from './disciplina.model';


@Component({
  selector: 'app-disciplina-form',
  templateUrl: './disciplina-form.component.html',
  styleUrls: []
})
export class DisciplinaFormComponent implements OnInit {

  acao: string;
  disciplina: Disciplina = new Disciplina();
  acoes = AcaoEnum;

  @BlockUI() blockUI: NgBlockUI;

  constructor(
    private disciplinaService: DisciplinaService,
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
    this.disciplinaService.findById(id).pipe(finalize(() => this.blockUI.stop()))
      .subscribe(disciplina => {
        this.disciplina = disciplina;
      });
  }

  persistirRegistro(form: NgForm){
    if (!form.valid){
      this.messageService.add({detail: 'Há campos obrigatórios que não foram informados!'});
      return;
    }
        this.blockUI.start('Salvando...');
        this.disciplinaService.save(this.disciplina).pipe(finalize(() => this.blockUI.stop()))
          .subscribe( () => {
            this.router.navigate( [ '/disciplina' ] );
            this.messageService.add(this.acao == this.acoes.INSERIR ? {detail: 'Disciplina incluida com sucesso'} : {detail: 'Disciplina alterada com sucesso'});
          });
  }
}
