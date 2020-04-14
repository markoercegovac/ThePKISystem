export class Certificate {
  id: number;
  serijskiBroj: string;
  vaziOd: string;
  vaziDo: string;
  alijas: string;


  constructor(id: number, serijskiBroj: string, vaziOd: string, vaziDo: string, alijas: string){
    this.id=id;
    this.serijskiBroj=serijskiBroj;
    this.vaziOd=vaziOd;
    this.vaziDo=vaziDo;
    this.alijas=alijas;
  }
}
