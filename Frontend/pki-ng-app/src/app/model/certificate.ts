export class Certificate {
  id: number;
  serialNumber: string;
  validFrom: string;
  validTo: string;
  alias: string;

  constructor(id: number, serialNumber: string, validFrom: string, validTo: string, alias: string){
    this.id=id;
    this.serialNumber=serialNumber;
    this.validFrom=validFrom;
    this.validTo=validTo;
    this.alias=alias;
  }
}
