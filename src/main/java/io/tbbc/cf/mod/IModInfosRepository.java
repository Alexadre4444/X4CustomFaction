package io.tbbc.cf.mod;

import java.util.List;

public interface IModInfosRepository {
    List<ModInfos> getAll();

    void update(ModInfos modInfos);

    void insert(ModInfos modInfos);
}
