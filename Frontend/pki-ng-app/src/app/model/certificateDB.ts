export class CertificateDB {
  serialNumber : string;
  type : string;
  valid : boolean;
  subjectCommonName : string;


  constructor(serialNumber: string, type : string ,valid : boolean , subjectCommonName : string) {
      this.serialNumber =serialNumber;
      this.type = type;
      this.valid = valid;
      this.subjectCommonName = subjectCommonName;

  }

}
