package com.vytra.administration;

import com.vytra.administration.entity.insurance.InsuranceType;
import com.vytra.administration.entity.insurance.Insurer;
import com.vytra.administration.entity.insurance.InsurerInsuranceType;
import com.vytra.administration.service.insurance.InsuranceTypeService;
import com.vytra.administration.service.insurance.InsurerInsuranceTypeService;
import com.vytra.administration.service.insurance.InsurerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Component
public class Seeds implements ApplicationRunner {


    @Autowired
    InsurerService insurerService;

    @Autowired
    InsuranceTypeService insuranceTypeService;

    @Autowired
    InsurerInsuranceTypeService insurerInsuranceTypeService;

    private final static Logger logger = Logger.getLogger("Insurance.seeds");

    @Override
    public void run(ApplicationArguments args) throws Exception {
        seedInsuranceTypes();
        seedInsurers();
        seedInsurerInsuranceTypes();
    }

    private void seedInsuranceTypes() {
        logger.info("Seeding insurance types ...");
        List<InsuranceType> listAll = new ArrayList<>((Collection<? extends InsuranceType>) insuranceTypeService.getAll());

        if (listAll.size() == 0) {
            List<InsuranceType> list = new ArrayList<>();
            list.add(new InsuranceType("Automoviles", "Seguro de automoviles", 1));
            list.add(new InsuranceType("ARL", "Riesgos laborales", 1));
            list.add(new InsuranceType("SOAT", "Seguro Obligatorio de Accidentes de Tránsito", 12));
            list.add(new InsuranceType("Transporte", "Seguro a las cargas", 1));
            list.add(new InsuranceType("Vida", "Seguros de vida y personas", 1));
            Iterable<InsuranceType> iterable = list;

            insuranceTypeService.saveAll(iterable);

            logger.info("Insurance types seeded");
        } else {
            logger.info("insurance types seeds haven been already seeded");
        }
    }

    private void seedInsurers() {
        logger.info("Seeding insurers ...");
        List<Insurer> listAll = new ArrayList<>((Collection<? extends Insurer>) insurerService.getAll());

        if (listAll.size() == 0) {
            List<Insurer> list = new ArrayList<>();
            list.add(new Insurer("860002183-9", "A.R.L. Seguros de Vida Colpatria S.A.", "A.R.L. Seguros de Vida Colpatria S.A."));
            list.add(new Insurer("800256161-9", "ARL Sura", "ARL Sura"));
            list.add(new Insurer("860503617-3", "ARP Alfa", "ARP Alfa"));
            list.add(new Insurer("800226175", "Colmena Riesgos Profesionales", "Colmena Riesgos Profesionales"));
            list.add(new Insurer("860002503-2", "Compañía de Seguros Bolívar S.A.", "Compañía de Seguros Bolívar S.A."));
            list.add(new Insurer("830008686-1", "La Equidad Seguros de Vida", "La Equidad Seguros de Vida"));
            list.add(new Insurer("860008645-7", "Liberty Seguros de Vida S.A.", "Liberty Seguros de Vida S.A."));
            list.add(new Insurer("8300549046", "Mapfre Colombia Vida Seguros S.A", "Mapfre Colombia Vida Seguros S.A"));
            list.add(new Insurer("860011153-6", "Positiva Compañía de Seguros", "Positiva Compañía de Seguros"));
            list.add(new Insurer("860022137-5", "Seguros de Vida Aurora", "Seguros de Vida Aurora"));

            list.add(new Insurer("8600261825", "Allianz", "Allianz Seguros S.A."));
            list.add(new Insurer("8605246546", "Aseguradora Solidaria ", "Aseguradora Solidaria de Colombia Ltda. Entidad Cooperativa"));
            list.add(new Insurer("8600021846", "AXA Colpatria", "AXA Colpatria Seguros  S.A."));
            list.add(new Insurer("9002004353", "Cardif", "Cardif Colombia Seguros Generales S.A."));
            list.add(new Insurer("8600284155", "Equidad Seguros", "La Equidad Seguros Generales Organismo Cooperativo"));
            list.add(new Insurer("8600399880", "Liberty Seguros", "Liberty Seguros C.A"));
            list.add(new Insurer("8917000379", "Mapfre", "Mapfre Seguros Generales de Colombia S.A."));
            list.add(new Insurer("8600024002", "Previsora Seguros", "La Previsora S.A. Compañia De Seguros"));
            list.add(new Insurer("8600025340", "QBE Seguros", "QBE Seguros S.A"));
            list.add(new Insurer("8300254485", "Seguros Bolivar", "Grupo Empresarial Bolivar Sociedades Bolivar S.A."));
            list.add(new Insurer("86003956580", "Seguros del Estado", "Seguros del Estado C.A"));
            list.add(new Insurer("8600370136", "Seguros Mundial", "Mundial de Seguros S.A"));
            list.add(new Insurer("8909034079", "Sura", "Seguros Generales Suramericana S.A."));

            Iterable<Insurer> iterable = list;

            insurerService.saveAll(iterable);

            logger.info("Insurers seeded");
        } else {
            logger.info("Insurance seeds haven been already seeded");
        }
    }

    private void seedInsurerInsuranceTypes() {
        logger.info("Seeding Insurers & Insurance types");
        List<InsurerInsuranceType> listAll = new ArrayList<>((Collection<? extends InsurerInsuranceType>) insurerInsuranceTypeService.getAll());

        if (listAll.size() == 0) {
            List<InsurerInsuranceType> list = new ArrayList<>();
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(1l);
            }}, new InsuranceType() {{
                setId(2l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(2l);
            }}, new InsuranceType() {{
                setId(2l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(3l);
            }}, new InsuranceType() {{
                setId(2l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(4l);
            }}, new InsuranceType() {{
                setId(2l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(5l);
            }}, new InsuranceType() {{
                setId(2l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(6l);
            }}, new InsuranceType() {{
                setId(2l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(7l);
            }}, new InsuranceType() {{
                setId(2l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(8l);
            }}, new InsuranceType() {{
                setId(2l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(9l);
            }}, new InsuranceType() {{
                setId(2l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(10l);
            }}, new InsuranceType() {{
                setId(2l);
            }}));

            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(11l);
            }}, new InsuranceType() {{
                setId(1l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(12l);
            }}, new InsuranceType() {{
                setId(1l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(13l);
            }}, new InsuranceType() {{
                setId(1l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(14l);
            }}, new InsuranceType() {{
                setId(1l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(15l);
            }}, new InsuranceType() {{
                setId(1l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(16l);
            }}, new InsuranceType() {{
                setId(1l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(17l);
            }}, new InsuranceType() {{
                setId(1l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(18l);
            }}, new InsuranceType() {{
                setId(1l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(19l);
            }}, new InsuranceType() {{
                setId(1l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(20l);
            }}, new InsuranceType() {{
                setId(1l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(21l);
            }}, new InsuranceType() {{
                setId(1l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(22l);
            }}, new InsuranceType() {{
                setId(1l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(23l);
            }}, new InsuranceType() {{
                setId(1l);
            }}));

            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(11l);
            }}, new InsuranceType() {{
                setId(3l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(12l);
            }}, new InsuranceType() {{
                setId(3l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(13l);
            }}, new InsuranceType() {{
                setId(3l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(14l);
            }}, new InsuranceType() {{
                setId(3l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(15l);
            }}, new InsuranceType() {{
                setId(3l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(16l);
            }}, new InsuranceType() {{
                setId(3l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(17l);
            }}, new InsuranceType() {{
                setId(3l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(18l);
            }}, new InsuranceType() {{
                setId(3l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(19l);
            }}, new InsuranceType() {{
                setId(3l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(20l);
            }}, new InsuranceType() {{
                setId(3l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(21l);
            }}, new InsuranceType() {{
                setId(3l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(22l);
            }}, new InsuranceType() {{
                setId(3l);
            }}));
            list.add(new InsurerInsuranceType(new Insurer() {{
                setId(23l);
            }}, new InsuranceType() {{
                setId(3l);
            }}));

            Iterable<InsurerInsuranceType> iterable = list;

            insurerInsuranceTypeService.saveAll(iterable);
            logger.info("Insurers & Insurance types seeded");
        } else {
            logger.info("Insurers & Insurance types seeds haven been already seeded");
        }
    }
}

