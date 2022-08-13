package fairyShop.models;

public class ShopImpl implements Shop{
    @Override
    public void craft(Present present, Helper helper) {
//        while (!present.isDone() && helper.canWork()) {
//            List<Instrument> workingInstrument = helper.getInstruments()
//                    .stream()
//                    .filter(i -> !i.isBroken())
//                    .limit(1)
//                    .collect(Collectors.toList());
//
//            if(workingInstrument.size() != 0) {
//                present.getCrafted();
//                helper.work();
//                workingInstrument.get(0).use();
//            }
//
//            if(present.isDone()) {
//                return;
//            }
//            if(!helper.canWork()) {
//                return;
//            }
//        }
        if (helper.canWork()) {
            for (Instrument instrument : helper.getInstruments()) {
                while (!instrument.isBroken()) {
                    if (!instrument.isBroken() && helper.canWork()) {
                        present.getCrafted();
                        helper.work();
                        instrument.use();
                    }
                    if (present.isDone()) {
                        return;
                    }
                    if (!helper.canWork()) {
                        return;
                    }
                }
            }
        }

    }
}
