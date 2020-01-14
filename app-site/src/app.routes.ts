import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import {AlunoComponent} from './app/pessoa/aluno.component';


import {AlunoFormComponent} from './app/pessoa/aluno-form.component';
import {DisciplinaComponent} from './app/disciplina/disciplina.component';
import {DisciplinaFormComponent} from './app/disciplina/disciplina-form.component';
import {MatriculaComponent} from './app/matricula/matricula.component';
import {MatriculaFormComponent} from './app/matricula/matricula-form.component';

const appRoutes: Routes = [
    { path: '',                        component: MatriculaComponent },
    { path: 'aluno',         component: AlunoComponent },
    { path: 'aluno/:acao/:id',         component: AlunoFormComponent },
    { path: 'aluno/:acao',         component: AlunoFormComponent },
    { path: 'disciplina',         component: DisciplinaComponent },
    { path: 'disciplina/:acao/:id',         component: DisciplinaFormComponent },
    { path: 'disciplina/:acao',         component: DisciplinaFormComponent },
    { path: 'matricula',         component: MatriculaComponent },
    { path: 'matricula/:acao/:id',         component: MatriculaFormComponent },
    { path: 'matricula/:acao',         component: MatriculaFormComponent }

];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
