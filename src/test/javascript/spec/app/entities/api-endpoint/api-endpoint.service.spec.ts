import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ApiEndpointService } from 'app/entities/api-endpoint/api-endpoint.service';
import { IApiEndpoint, ApiEndpoint } from 'app/shared/model/api-endpoint.model';
import { ApiEndpointVendor } from 'app/shared/model/enumerations/api-endpoint-vendor.model';

describe('Service Tests', () => {
  describe('ApiEndpoint Service', () => {
    let injector: TestBed;
    let service: ApiEndpointService;
    let httpMock: HttpTestingController;
    let elemDefault: IApiEndpoint;
    let expectedResult: IApiEndpoint | IApiEndpoint[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ApiEndpointService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ApiEndpoint(0, ApiEndpointVendor.INFOBIP, 'AAAAAAA', false, 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ApiEndpoint', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ApiEndpoint()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ApiEndpoint', () => {
        const returnedFromService = Object.assign(
          {
            vendor: 'BBBBBB',
            type: 'BBBBBB',
            enabled: true,
            rate: 1,
            url: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ApiEndpoint', () => {
        const returnedFromService = Object.assign(
          {
            vendor: 'BBBBBB',
            type: 'BBBBBB',
            enabled: true,
            rate: 1,
            url: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ApiEndpoint', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
