package fairyShop.core;

import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private HelperRepository<Helper> helperRepository = new HelperRepository<>();
    private PresentRepository<Present> presentRepository = new PresentRepository<>();
    private Shop shop = new ShopImpl();

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;
        if (type.equals("Happy")) {
            helper = new Happy(helperName);
        } else if (type.equals("Sleepy")) {
            helper = new Sleepy(helperName);
        } else {
            throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }
        helperRepository.add(helper);
        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Instrument instrument = new InstrumentImpl(power);
        Helper helperByName = helperRepository.findByName(helperName);
        if (helperByName == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }

        helperByName.addInstrument(instrument);
        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        presentRepository.add(present);

        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        Collection<Helper> helpersToWork = helperRepository
                .getModels()
                .stream()
                .filter(h -> h.getEnergy() > 50)
                .collect(Collectors.toList());

        if (helpersToWork.isEmpty()) {
            throw new IllegalArgumentException(NO_HELPER_READY);
        }

        int brokenInstruments = 0;
        Present present = presentRepository.findByName(presentName);
        for (Helper helper : helpersToWork) {
            shop.craft(present, helper);
            brokenInstruments += helper.getInstruments().stream().filter(Instrument::isBroken).count();

            if (present.isDone()) {
                break;
            }
        }

        if (present.isDone()) {
            return String.format(PRESENT_DONE, presentName, "done") +
                    String.format(COUNT_BROKEN_INSTRUMENTS, brokenInstruments);
        }

        return String.format(PRESENT_DONE, presentName, "not done") +
                String.format(COUNT_BROKEN_INSTRUMENTS, brokenInstruments);

    }

    @Override
    public String report() {
        long craftedPresentsCount = presentRepository.getModels().stream().filter(Present::isDone).count();

        List<String> helpersInfo = helperRepository.getModels()
                .stream()
                .map(helper -> String.format("Name: %s%n" +
                                "Energy: %d%n" +
                                "Instruments: %d not broken left%n",
                        helper.getName(),
                        helper.getEnergy(),
                        (int) helper.getInstruments().stream().filter(i -> !i.isBroken()).count())).collect(Collectors.toList());


        return String.format("%d presents are done!%n", craftedPresentsCount) +
                String.format("Helpers info:%n") +
                String.join("", helpersInfo).trim();

    }
}

