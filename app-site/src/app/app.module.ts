import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AlunoComponent} from './pessoa/aluno.component';

import {routing} from './../app.routes';


import {ConfigService} from './services/config.service';
import {AccordionModule, ConfirmationService, InputMaskModule, MessageService, ScrollPanelModule} from 'primeng';
import {MenuComponent} from './menu/menu.component';
import {AlunoFormComponent} from './pessoa/aluno-form.component';
import {PRIMENG_IMPORTS} from './primeng-imports';
import {HttpClientModule} from '@angular/common/http';
import {AlunoService} from './pessoa/aluno.service';
import {BrowserAnimationsModule, NoopAnimationsModule} from '@angular/platform-browser/animations';
import {DisciplinaComponent} from './disciplina/disciplina.component';
import {DisciplinaFormComponent} from './disciplina/disciplina-form.component';
import {MatriculaFormComponent} from './matricula/matricula-form.component';
import {MatriculaComponent} from './matricula/matricula.component';


@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    AlunoComponent,
    AlunoFormComponent,
    DisciplinaComponent,
    DisciplinaFormComponent,
    MatriculaFormComponent,
    MatriculaComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    routing,
    ScrollPanelModule,
    InputMaskModule,
    PRIMENG_IMPORTS,
    BrowserAnimationsModule,
    NoopAnimationsModule,
    AccordionModule
  ],
  providers: [ConfigService, AlunoService, MessageService, ConfirmationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
