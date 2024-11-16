import { Component, OnInit } from '@angular/core';
import { ReclamoService } from '../../service/usuario.service';  // Asegúrate de que la ruta al servicio sea correcta
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export default class HomeComponent implements OnInit {
  tipoDocumentos: any[] = [];
  
  departamentos: any[] = [];
  FilterDepartamento: any[]=[]; 

  provincias: any[] = [];
  FilterProvincias: any[]=[]; 

  distritos: any[] = [];
  FilterDistritos: any[]=[]; 

  reclamos: any[] = [];

  // Datos del reclamo a registrar
  reclamo = {
    nombreCompleto: '',
    documento: '',
    tipoDocumentoId: '',   // Aquí se guardará el ID seleccionado del tipo de documento
    departamentoId: '',    // Aquí se guardará el ID seleccionado del departamento
    provinciaId: '',       // Aquí se guardará el ID seleccionado de la provincia
    distritoId: '',        // Aquí se guardará el ID seleccionado del distrito
    domicilio: '',
    correoElectronico: '',
    telefono: '',
    celular: '',
    fechaHecho: '',
    funcionario: '',
    descripcion: ''
  };

  errorMessage = '';
  successMessage = '';
  openmodal = false;
  readonlyMode = false;

  constructor(private reclamoService: ReclamoService) {}

  ngOnInit(): void {
    this.getTiposDocumentos();
    this.getDepartamentos();
    this.getProvincias();
    this.getDistritos();
    this.getReclamos();
  }

  OpenModalButton() {
    this.openmodal = true;
  }

  CloseModalButton() {
    this.openmodal = false;
  }

  // Método para obtener los tipos de documentos desde la API
  getTiposDocumentos(): void {
    this.reclamoService.getTiposDocumentos().subscribe(
      res => {
        this.tipoDocumentos = res;
      },
      error => {
        this.errorMessage = 'Error al obtener los tipos de documentos';
      }
    );
  }

  // Método para obtener los departamentos desde la API
  getDepartamentos(): void {
    this.reclamoService.getDepartamentos().subscribe(
      res => {
        this.departamentos = res;
        this.FilterDepartamento = res;
      },
      error => {
        this.errorMessage = 'Error al obtener los departamentos';
      }
    );
  }

  // Método para obtener las provincias desde la API
  getProvincias(): void {
    this.reclamoService.getProvincias().subscribe(
      res => {
        this.provincias = res;
        this.FilterProvincias = res;
      },
      error => {
        this.errorMessage = 'Error al obtener las provincias';
      }
    );
  }

  // Método para obtener los distritos desde la API
  getDistritos(): void {
    this.reclamoService.getDistritos().subscribe(
      res => {
        this.distritos = res;
        this.FilterDistritos = res;
      },
      error => {
        this.errorMessage = 'Error al obtener los distritos';
      }
    );
  }

  // Método para obtener los reclamos ya registrados
  getReclamos(): void {
    this.reclamoService.getReclamos().subscribe(
      res => {
        this.reclamos = res;
      },
      error => {
        this.errorMessage = 'Error al obtener los reclamos';
      }
    );
  }
  // Método para registrar un nuevo reclamo
  registrarReclamo(): void {
    this.reclamoService.createReclamo(this.reclamo).subscribe(
      res => {
        this.successMessage = 'Reclamo registrado con éxito';
        this.getReclamos();  // Recarga los reclamos después de registrar uno nuevo
        
        this.cerrarFormulario();
      },
      error => {
        this.errorMessage = 'Error al registrar el reclamo';
      }
    );
  }
  
  verReclamo(id: number): void {
    const reclamoSeleccionado = this.reclamos.find(r => r.id === id);
    if (reclamoSeleccionado) {
      this.reclamo = { ...reclamoSeleccionado };
      this.readonlyMode = true; // Activamos el modo solo lectura
      this.openmodal = true; // Abrimos el modal para ver el reclamo
    }
  }

  // Método para cerrar el formulario
  cerrarFormulario() {
    this.reclamo = {
      nombreCompleto: '',
      documento: '',
      tipoDocumentoId: '',
      departamentoId: '',
      provinciaId: '',
      distritoId: '',
      domicilio: '',
      correoElectronico: '',
      telefono: '',
      celular: '',
      fechaHecho: '',
      funcionario: '',
      descripcion: ''
    };
    this.openmodal = false;
  }
  UpdateDate(event: Event){
    const inputElement = event.target as HTMLInputElement;
    this.reclamo.fechaHecho = inputElement.value;
    console.log(this.reclamo.fechaHecho);
  }
  EscogeDepartamento(event: Event){
    const inputElement = event.target as HTMLSelectElement;
    const idDepartamento = inputElement.value;
    this.FilterProvincias = this.provincias.filter((provincia) => provincia.departamentoId == idDepartamento);
  }
  EscogeProvincia(event: Event){
    const inputElement = event.target as HTMLSelectElement;
    const idProvincia = inputElement.value;
    this.FilterDistritos = this.distritos.filter((distrito) => distrito.provinciaId == idProvincia);
  }
  EscogeDistrito(event: Event){
    const inputElement = event.target as HTMLSelectElement;
  }
}
