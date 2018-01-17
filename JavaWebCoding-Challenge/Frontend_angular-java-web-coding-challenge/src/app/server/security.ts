import {AES, enc} from 'crypto-js';

export class Security {

  constructor() {
  }

  static encrypt(text: string): any {
    // padding and truncating
    return AES.encrypt( text.trim(), 'this-is-my-secret-key-02811da22377d62fcfdb02f29aad77d9e');

  }

  static decrypt(encrypted_text): string {
    let decrepted = AES.decrypt(encrypted_text, 'this-is-my-secret-key-02811da22377d62fcfdb02f29aad77d9e');
    return decrepted.toString(enc.Utf8);
  }

}
