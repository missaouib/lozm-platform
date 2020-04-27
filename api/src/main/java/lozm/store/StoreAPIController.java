package lozm.store;

import lombok.RequiredArgsConstructor;
import lozm.dto.APIResponseDto;
import lozm.dto.item.GetClothingDto;
import lozm.dto.item.GetItemDto;
import lozm.dto.item.PostItemDto;
import lozm.dto.item.PutItemDto;
import lozm.dto.store.GetStoreDto;
import lozm.dto.store.PostStoreDto;
import lozm.dto.store.PutStoreDto;
import lozm.entity.store.Store;
import lozm.vo.item.ItemVo;
import lozm.vo.store.StoreVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/store")
@RestController
@RequiredArgsConstructor
public class StoreAPIController {

    private final StoreService storeService;


    @GetMapping
    public APIResponseDto getStore() {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            List<GetStoreDto> storeList = storeService.findAllStores();
            GetStoreDto.Response storeResDto = new GetStoreDto.Response();
            storeResDto.setList(storeList);

            resDto.setSuccess(true);
            resDto.setData(storeResDto);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @GetMapping(value = "/{storeId}")
    public APIResponseDto getStoreDetail(@PathVariable(value = "storeId") Long storeId) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            StoreVo storeVo = StoreVo.builder()
                    .id(storeId)
                    .build();

            Store store = storeService.findById(storeVo);
            GetStoreDto storeResDto = new GetStoreDto(store.getId(), store.getName(), store.getTelNumber(), store.getInfo());
            resDto.setData(storeResDto);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PostMapping
    public APIResponseDto postStore(@RequestBody @Valid PostStoreDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            StoreVo storeVo = StoreVo.builder()
                    .name(reqDto.getName())
                    .telNumber(reqDto.getTelNumber())
                    .info(reqDto.getInfo())
                    .build();

            storeService.save(storeVo);
            resDto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PutMapping
    public APIResponseDto putStore(@RequestBody @Valid PutStoreDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            StoreVo storeVo = StoreVo.builder()
                    .id(reqDto.getId())
                    .name(reqDto.getName())
                    .telNumber(reqDto.getTelNumber())
                    .info(reqDto.getInfo())
                    .build();

            storeService.update(storeVo);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

}
