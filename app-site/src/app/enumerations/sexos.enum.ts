import {SelectItem} from 'primeng';

export class SexoEnum {
  static F = 'F';
  static M = 'M';
  static MASCULINO = 'Masculino';
  static FEMININO = 'Feminino';
  static sexosSelectItem: SelectItem[] = [
    {value: SexoEnum.F, label: SexoEnum.FEMININO},
    {value: SexoEnum.M, label: SexoEnum.MASCULINO}
  ];
}
