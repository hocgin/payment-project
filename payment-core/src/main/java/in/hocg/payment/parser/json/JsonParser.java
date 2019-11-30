package in.hocg.payment.parser.json;

import in.hocg.payment.parser.Parser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2019/11/29.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RequiredArgsConstructor
public class JsonParser<T> implements Parser<T> {
    
    @NonNull
    private Class<T> clazz;
    
    @Override
    public T parse(String text) {
        return new JsonConverter().to(text, clazz);
    }
    
    @Override
    public Class<T> getTargetClass() {
        return this.clazz;
    }
}
